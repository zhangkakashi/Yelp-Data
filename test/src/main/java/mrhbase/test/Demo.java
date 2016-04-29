package mrhbase.test;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.HFileOutputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;

public class Demo {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Path input = new Path(args[0]);
		Path output = new Path(args[1]);
		String tableName = "test_mr_hbase";
		
		Configuration config = HBaseConfiguration.create();
		config.set("hbase.table.name", tableName);
		
		Job job = Job.getInstance(config, "Test inport");
		job.setJarByClass(Demo.class);
		
		job.setMapperClass(HBaseMapper.class);
		job.setMapOutputKeyClass(ImmutableBytesWritable.class);
		job.setMapOutputValueClass(KeyValue.class);
		
		
		job.setInputFormatClass(TextInputFormat.class);
		
		HTable table = new HTable(config, tableName);
		
		HFileOutputFormat.configureIncrementalLoad(job, table);
		
		
		
	}

}
