package yelp.business;

import java.io.IOException;
import java.util.TreeMap;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.HTableUtil;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.mapreduce.TableOutputFormat;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.codehaus.jackson.map.ObjectMapper;

import Parser.JSONArray;
import Parser.JSONException;
import Property.Business;
import Property.InfoWritable;

public class BusinessMR {
	public static class JsonMapper extends
			Mapper<Object, Text, Text, InfoWritable> {

		private InfoWritable info = new InfoWritable();

		public void map(Object key, Text value, Context context)
				throws IOException, InterruptedException {
			try {
				//Configuration config = HBaseConfiguration.create();
				//HTable hTable = new HTable(config, TABLE_NAME);

				
				
				ObjectMapper mapper = new ObjectMapper();
				String[] tuple = value.toString().split("\\n");

				for (int i = 0; i < tuple.length; i++) {
					Business bus = mapper.readValue(tuple[i], Business.class);

					// if(bus.reviewCount != null &&
					// Integer.parseInt(bus.reviewCount) > 3)
					// {
					JSONArray catArr = new JSONArray();
					JSONArray neiArr = new JSONArray();
					info.setBusinessId(bus.businessId + "");
					info.setType(bus.type + "");
					info.setFull_address(bus.full_address + "");
					info.setCity(bus.city + "");
					info.setState(bus.state + "");
					info.setLatitude(bus.latitude + "");
					info.setLongitude(bus.longitude + "");
					info.setName(bus.name + "");
					info.setStars(bus.stars + "");
					info.setReviewCount(bus.reviewCount + "");
					info.setOpen(bus.open + "");
					info.setCategory(catArr.put(bus.category).toString() + "");
					info.setNeighborhood(neiArr.put(bus.neighborhood).toString() + "");
					if (info != null && bus.businessId != null) {
						Text te = new Text(bus.businessId);
						
						/*
						Put put = new Put(Bytes.toBytes(bus.businessId));
						put.add(Bytes.toBytes("business"), Bytes.toBytes("TYPE"), Bytes.toBytes(info.getType()));
											
						put.add(Bytes.toBytes("business"), Bytes.toBytes("NAME"), Bytes.toBytes(info.getName()));
												
						put.add(Bytes.toBytes("business"), Bytes.toBytes("FULL_ADDRESS"), Bytes.toBytes(info.getFull_address()));
												
						put.add(Bytes.toBytes("business"), Bytes.toBytes("CITY"), Bytes.toBytes(info.getCity()));
												
						put.add(Bytes.toBytes("business"), Bytes.toBytes("STATE"), Bytes.toBytes(info.getState()));

						put.add(Bytes.toBytes("business"), Bytes.toBytes("OPEN"), Bytes.toBytes(info.getOpen()));

						put.add(Bytes.toBytes("business"), Bytes.toBytes("CATEGORY"), Bytes.toBytes(info.getCategory()));

						put.add(Bytes.toBytes("business"), Bytes.toBytes("LATITUDE"), Bytes.toBytes(info.getLatitude()));

						put.add(Bytes.toBytes("business"), Bytes.toBytes("LONGITUDE"), Bytes.toBytes(info.getLongitude()));

						put.add(Bytes.toBytes("business"), Bytes.toBytes("STARS"), Bytes.toBytes(info.getStars()));

						put.add(Bytes.toBytes("business"), Bytes.toBytes("REVIEW_COUNT"), Bytes.toBytes(info.getReviewCount()));

						put.add(Bytes.toBytes("business"), Bytes.toBytes("NEIGHBORHOOD"), Bytes.toBytes(info.getNeighborhood()));
							*/
						//hTable.put(put);
						context.write(te, info);
					}
					// }
				}
				//hTable.close();

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		}
	}

	public static class JsonReducer extends
			Reducer<Text, InfoWritable, Text, Text> {
		// private InfoWritable info = new InfoWritable();
		private TreeMap<Text, Text> tree = new TreeMap<Text, Text>();

		public void reduce(Text key, Iterable<InfoWritable> values,
				Context context) throws IOException, InterruptedException {
			for(InfoWritable info: values){
				
				String value = info.getType() + "  " + info.getName() + "  " + info.getFull_address() + "  "
						+ info.getCity() + "  " + info.getState() + "  " + info.getOpen() + "  " + info.getCategory()
						+ "  " + info.getLatitude() + "  " + info.getLongitude() + "  " +info.getStars() + "  " 
						+ info.getReviewCount() + "  " + info.getNeighborhood();
				
				
				context.write(key, new Text(value));
				
			}
				
			
			
		}
	}

	public static void main(String[] args) throws Exception {
		runJob(args[0], args[1]);
		//runJob(args[0], "");
	}

	
	private static String TABLE_NAME = "business_test";
	
	 
	public static void runJob(String input, String output) throws Exception {
		
		//Configuration config = HBaseConfiguration.create();
		//Configuration conf = HTableUtil.getConf();
		Configuration conf = new Configuration();
		Job job = new Job(conf);
		job.setJarByClass(BusinessMR.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(InfoWritable.class);
		job.setMapperClass(JsonMapper.class);
		job.setReducerClass(JsonReducer.class);
		// job.setInputFormatClass(KeyValueTextInputFormat.class);
		job.setNumReduceTasks(1);
		job.setOutputFormatClass(TextOutputFormat.class);

		FileInputFormat.setInputPaths(job, new Path(input));
		Path outPath = new Path(output);
		FileOutputFormat.setOutputPath(job, outPath);
		outPath.getFileSystem(conf).delete(outPath, true);

		job.waitForCompletion(true);
		
		
		/*
		Configuration conf = new Configuration();
        conf.set(TableOutputFormat.OUTPUT_TABLE, TABLE_NAME);
        //HbaseUtil.createHBaseTable(TABLE_NAME, COLUMN_NAME);
 
        //String input = args[0];
        Job job = new Job(conf, "HBaseCalSum table with " + input);
        
        job.setJarByClass(BusinessMR.class);
        
        
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(InfoWritable.class);
        job.setMapperClass(JsonMapper.class);
        job.setReducerClass(JsonReducer.class);
        //job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TableOutputFormat.class);
 
        FileInputFormat.addInputPath(job, new Path(input));
 
        int retJob = job.waitForCompletion(true)?0:1;
         
        System.exit(retJob);
        */
	}
}
