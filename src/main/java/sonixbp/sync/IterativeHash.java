package sonixbp.sync;

import cloudbase.core.client.Connector;
import cloudbase.core.client.Instance;
import cloudbase.core.client.ZooKeeperInstance;
import cloudbase.core.client.mapreduce.CloudbaseFileOutputFormat;
import cloudbase.core.client.mapreduce.CloudbaseInputFormat;
import cloudbase.core.client.mapreduce.lib.partition.RangePartitioner;
import cloudbase.core.security.Authorizations;
import cloudbase.core.util.TextUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.BufferedOutputStream;
import java.io.PrintStream;
import java.util.Collection;

public class IterativeHash extends Configured implements Tool {


    // first we need to calculate the partition value (default 48*1000*60*60or 172800000ms).

    // calculate each hour (from the current hour back) and get the reverse indexed date ranges that will need to be queried

    // set the input ranges of the CloudbaseInputFormat to this list of ranges

    private String inputTable = null;
	private String zookeepers = "localhost";
	private String username = null;
	private String password = null;
	private String instanceName = null;
    private String outputDir = null;

    public IterativeHash(String zookeepers, String instanceName, String username, String password, String outputDir,
                         String inputTable) {

        this.zookeepers = zookeepers;
        this.instanceName = instanceName;
        this.username = username;
        this.password = password;
        this.outputDir = outputDir;
        this.inputTable = inputTable;
    }

    @Override
    public int run(String[] strings) throws Exception {

		// establish the configuration
		Configuration conf = getConf();

		String jobStr = new String("Cloudbase dump for table: " + inputTable);

		Job job = new Job(conf, jobStr);

		job.setJarByClass(getClass());
		job.setMapperClass(IterativeHash.class);

        job.setOutputFormatClass(CloudbaseFileOutputFormat.class);
        job.setInputFormatClass(CloudbaseInputFormat.class);
		job.getConfiguration().set("mapred.task.timeout", "0");
		job.getConfiguration().set("mapred.map.tasks.speculative.execution", "false");

		job.setNumReduceTasks(0);

        Instance instance = new ZooKeeperInstance(instanceName, zookeepers);
        Connector connector = instance.getConnector(username, password.getBytes());

        Authorizations auths = connector.securityOperations().getUserAuthorizations(connector.whoami());
        CloudbaseInputFormat.setInputInfo(job, username, password.getBytes(), inputTable, auths);
        CloudbaseInputFormat.setZooKeeperInstance(job, instanceName, zookeepers);

        CloudbaseFileOutputFormat.setOutputPath(job, new Path(outputDir + "/data"));

        FileSystem fs = FileSystem.get(conf);
        PrintStream out = new PrintStream(new BufferedOutputStream(fs.create(new Path(outputDir + "/splits.txt"))));

        Collection<Text> splits = connector.tableOperations().getSplits(inputTable, 100);
        for (Text split : splits)
            out.println(new String(Base64.encodeBase64(TextUtil.getBytes(split))));

        out.close();

        job.setPartitionerClass(RangePartitioner.class);
        RangePartitioner.setSplitFile(job, outputDir + "/splits.txt");

		int retVal = job.waitForCompletion(false) ? 0: -1;

		return retVal;
    }

    public static void main(final String[] args) throws Exception {

        ExecutionContext context = new ExecutionContext();
        context.addOption(new ExecOption("outdir", "an output directory to use for dump", false, null));
        context.addOption(new ExecOption("zk", "zookeepers servers", false, null));
        context.addOption(new ExecOption("u", "cloudbase username", false, null));
        context.addOption(new ExecOption("p", "cloudbase password", false, null));
        context.addOption(new ExecOption("inst", "cloudbase instance", false, null));
        context.addOption(new ExecOption("table", "table to dump", false, null));

        if(!context.parseArgs(args))
        {
        	System.out.println(context.getUseAsString(CloudbaseDump.class));
            return;
        }

        String zookeepers = context.get("zk");
        String inputTable = context.get("table");
        String outputDir = context.get("outdir");
        String instance = context.get("inst");
        String username = context.get("u");
        String password = context.get("p");

        Tool cloudbaseDump = new CloudbaseDump(zookeepers, instance, username, password, outputDir, inputTable);

        Configuration conf = new Configuration();
        conf.addResource(new Path("/Library/Hadoop/Home/conf/core-site.xml"));
        conf.addResource(new Path("/Library/Hadoop/Home/conf/hdfs-site.xml"));

        ToolRunner.run(conf, cloudbaseDump, args);
    }



}
