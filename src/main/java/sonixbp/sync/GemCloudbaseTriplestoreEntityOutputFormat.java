package sonixbp.sync;


import cloudbase.core.client.CBException;
import cloudbase.core.client.CBSecurityException;
import cloudbase.core.client.Connector;
import cloudbase.core.client.ZooKeeperInstance;
import cloudbase.core.util.ArgumentChecker;
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

public class GemCloudbaseTriplestoreEntityOutputFormat extends OutputFormat {

    private static final String PREFIX = GemCloudbaseTriplestoreEntityOutputFormat.class.getSimpleName();
    private static final String OUTPUT_INFO_HAS_BEEN_SET = PREFIX + ".configured";
    private static final String INSTANCE_HAS_BEEN_SET = PREFIX + ".instanceConfigured";

    private static final String USERNAME = PREFIX + ".username";
    private static final String PASSWORD = PREFIX + ".password";

    private static final String INSTANCE_NAME = PREFIX + ".instanceName";
    private static final String ZOOKEEPERS = PREFIX + ".zooKeepers";

    public static void setOutputInfo(JobContext job, String username, byte[] password) {

        Configuration conf = job.getConfiguration();
        conf.set(USERNAME, username);
        conf.set(PASSWORD, new String(Base64.encodeBase64(password)));
        conf.setBoolean(OUTPUT_INFO_HAS_BEEN_SET, true);
    }
    public static void setZookeeperInstance(JobContext job, String instance, String zookeepers) {

        Configuration conf = job.getConfiguration();
        conf.set(INSTANCE_NAME, instance);
        conf.set(ZOOKEEPERS, zookeepers);
        conf.setBoolean(INSTANCE_HAS_BEEN_SET, true);
    }

    public ZooKeeperInstance getZookeeperInstance(JobContext job) {

        String instance = job.getConfiguration().get(INSTANCE_NAME);
        String zookeepers = job.getConfiguration().get(ZOOKEEPERS);

        return new ZooKeeperInstance(instance, zookeepers);
    }

    public String getUsername(JobContext job) {
        return job.getConfiguration().get(USERNAME);
    }

    public byte[] getPassword(JobContext job) {
        return Base64.decodeBase64(job.getConfiguration().get(PASSWORD).getBytes());
    }

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

    @Override
    public RecordWriter<Text, BasicEntity> getRecordWriter(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {

        try {

            return new GemEntityRecordWriter(taskAttemptContext);
        }

        catch(Exception e) {

            throw new IOException("Error returning new GemEntityRecordWriter");
        }
    }

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

    @Override
    public OutputCommitter getOutputCommitter(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {

       return new NullOutputFormat<Text, BasicEntity>().getOutputCommitter(taskAttemptContext);
    }

    protected class GemEntityRecordWriter extends RecordWriter<Text, BasicEntity> {

        private EntityService entityService;

        public GemEntityRecordWriter(JobContext job) {

            this.entityService = getEntityService(job);
        }

        @Override
        public void write(Text text, BasicEntity basicEntity) throws IOException, InterruptedException {

            entityService.save(basicEntity);
        }

        @Override
        public void close(TaskAttemptContext context) throws IOException {}
    }
}
