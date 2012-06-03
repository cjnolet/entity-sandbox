package sonixbp.sync;


import cloudbase.core.client.CBException;
import cloudbase.core.client.CBSecurityException;
import cloudbase.core.client.Connector;
import cloudbase.core.client.ZooKeeperInstance;
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

    public ZooKeeperInstance getZookeeperInstance(JobContext job) {

        String instance = job.getConfiguration().get(INSTANCE_NAME);
        String zookeepers = job.getConfiguration().get(ZOOKEEPERS);

        return new ZooKeeperInstance(instance, zookeepers);
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

        return new GemEntityRecordWriter(taskAttemptContext);
    }

    @Override
    public void checkOutputSpecs(JobContext jobContext) throws IOException, InterruptedException {}

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
