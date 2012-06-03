package sonixbp.sync;


import cloudbase.core.client.CBException;
import cloudbase.core.client.CBSecurityException;
import cloudbase.core.client.Connector;
import cloudbase.core.client.ZooKeeperInstance;
import org.apache.commons.codec.binary.Base64;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.OutputCommitter;
import org.apache.hadoop.mapreduce.OutputFormat;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.NullOutputFormat;
import sonixbp.domain.BasicEntity;
import sonixbp.service.EntityService;
import sonixbp.service.impl.CloudbaseTriplestore;
import sonixbp.service.impl.TriplestoreEntityService;

import java.io.IOException;
import java.util.ArrayList;


public class GemCloudbaseTriplestoreEntityOutputFormat extends OutputFormat {

    private static final String PREFIX = GemCloudbaseTriplestoreEntityOutputFormat.class.getSimpleName();
    private static final String OUTPUT_INFO_HAS_BEEN_SET = PREFIX + ".configured";
    private static final String INSTANCE_HAS_BEEN_SET = PREFIX + ".instanceConfigured";

    private static final String USERNAME = PREFIX + ".username";
    private static final String PASSWORD = PREFIX + ".password";

    private static final String INSTANCE_NAME = PREFIX + ".instanceName";
    private static final String ZOOKEEPERS = PREFIX + ".zooKeepers";

    private static final int DEFAULT_BUFFER_SIZE = 500;
    private static final String BUFFER_SIZE = ".bufferSize";

    /**
     * Sets the Cloudbase username/password on the job
     * @param job
     * @param username
     * @param password
     */
    public static void setOutputInfo(JobContext job, String username, byte[] password) {

        Configuration conf = job.getConfiguration();
        conf.set(USERNAME, username);
        conf.set(PASSWORD, new String(Base64.encodeBase64(password)));
        conf.setBoolean(OUTPUT_INFO_HAS_BEEN_SET, true);
    }

    /**
     * Sets the Zookeeper & Cloudbase Instance information on the job
     * @param job
     * @param instance
     * @param zookeepers
     */
    public static void setZookeeperInstance(JobContext job, String instance, String zookeepers) {

        Configuration conf = job.getConfiguration();
        conf.set(INSTANCE_NAME, instance);
        conf.set(ZOOKEEPERS, zookeepers);
        conf.setBoolean(INSTANCE_HAS_BEEN_SET, true);
    }

    public static void setBufferSize(JobContext job, int bufferSize) {

        Configuration conf = job.getConfiguration();
        conf.setInt(BUFFER_SIZE, bufferSize);
    }

    /**
     * Builds a new Cloudbase Zookeeper Instance based on info that was set on the job
     * @param job
     * @return
     */
    protected ZooKeeperInstance getZookeeperInstance(JobContext job) {

        String instance = job.getConfiguration().get(INSTANCE_NAME);
        String zookeepers = job.getConfiguration().get(ZOOKEEPERS);

        return new ZooKeeperInstance(instance, zookeepers);
    }

    /**
     * Returns the Cloudbase Username that was set on the job
     * @param job
     * @return
     */
    protected String getUsername(JobContext job) {
        return job.getConfiguration().get(USERNAME);
    }

    /**
     * Returns the Cloudbase password (decoded from base64) that was set on the job
     * @param job
     * @return
     */
    protected byte[] getPassword(JobContext job) {
        return Base64.decodeBase64(job.getConfiguration().get(PASSWORD).getBytes());
    }

    /**
     * Builds the Gem Entity Service for saving the entities
     * @param job
     * @return
     */
    public TriplestoreEntityService getEntityService(JobContext job) {

        Configuration conf = job.getConfiguration();
        ZooKeeperInstance instance = getZookeeperInstance(job);
        Connector connector = null;
        try {
            connector = instance.getConnector(conf.get(USERNAME), conf.get(PASSWORD));
        } catch (CBException e) {
            e.printStackTrace();
        } catch (CBSecurityException e) {
            e.printStackTrace();
        }

        CloudbaseTriplestore triplestore = new CloudbaseTriplestore(connector);
        return new TriplestoreEntityService(triplestore);
    }

    /**
     * Builds a new Gem Entity Record Writer
     * @param taskAttemptContext
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    public RecordWriter<Text, BasicEntity> getRecordWriter(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {

        try {

            return new GemCloudbaseTriplestoreEntityRecordWriter(taskAttemptContext);
        }

        catch(Exception e) {

            throw new IOException("Error returning new GemCloudbaseTriplestoreEntityRecordWriter");
        }
    }

    /**
     * Verifies that correct inputs are set so that the job can fail gracefully.
     * @param job
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    public void checkOutputSpecs(JobContext job) throws IOException, InterruptedException {
        Configuration conf = job.getConfiguration();
        if (!conf.getBoolean(OUTPUT_INFO_HAS_BEEN_SET, false))
            throw new IOException("Output info has not been set.");
        if (!conf.getBoolean(INSTANCE_HAS_BEEN_SET, false))
            throw new IOException("Instance info has not been set.");
        try {
            Connector c = getZookeeperInstance(job).getConnector(getUsername(job), getPassword(job));
            if (!c.securityOperations().authenticateUser(getUsername(job), getPassword(job)))
                throw new IOException("Unable to authenticate user");
        } catch (CBException e) {
            throw new IOException(e);
        } catch (CBSecurityException e) {
            throw new IOException(e);
        }
    }

    /**
     * Builds the empty output committer.
     * @param taskAttemptContext
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    public OutputCommitter getOutputCommitter(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {

       return new NullOutputFormat<Text, BasicEntity>().getOutputCommitter(taskAttemptContext);
    }

    /**
     * Class for writing entities to the underlying entity service via a streamable output buffer
     */
    protected class GemCloudbaseTriplestoreEntityRecordWriter extends RecordWriter<Text, BasicEntity> {

        private EntityService entityService;
        private int bufferSize = DEFAULT_BUFFER_SIZE;

        private BasicEntity[] entityBuffer;

        public GemCloudbaseTriplestoreEntityRecordWriter(JobContext job) {

            this.entityService = getEntityService(job);
            this.bufferSize = job.getConfiguration().getInt(BUFFER_SIZE, DEFAULT_BUFFER_SIZE);

            entityBuffer = new BasicEntity[bufferSize];
        }

        /**
         * Write method adds entity to the buffer (and dumps the buffer to the entity service it's full)
         * @param text
         * @param basicEntity
         * @throws IOException
         * @throws InterruptedException
         */
        @Override
        public void write(Text text, BasicEntity basicEntity) throws IOException, InterruptedException {

            try {

                if(entityBuffer.length < bufferSize) {
                    entityBuffer[entityBuffer.length] = basicEntity;
                }

                else {
                    entityService.save(entityBuffer);
                    entityBuffer = new BasicEntity[bufferSize];
                }
            }

            catch(Exception e) {

                throw new IOException("An error occurred saving entity <" + entityService.toString() + ">");
            }
        }

        /**
         * Dumps any entities left in the buffer to the entity service.
         * @param context
         * @throws IOException
         */
        @Override
        public void close(TaskAttemptContext context) throws IOException {

            if(entityBuffer.length > 0) {
                entityService.save(entityBuffer);
            }
        }
    }
}