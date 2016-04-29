package mrhbase.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class HBaseMapper extends Mapper<LongWritable, Text, ImmutableBytesWritable, KeyValue>{
	
	SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-DD hh:mm:ss");
	ImmutableBytesWritable hkey = new ImmutableBytesWritable();
	KeyValue kv;
	
	@Override
	protected void setup(Context context){
		
	}
	
	@Override
	protected void map(LongWritable key, Text value, Context context){
		if(value.find("medallion") > -1){
			return;
		}
		try{
			String[] fields = value.toString().split(",");
			
			if(fields.length == 14){
				String medallion = fields[0];
			}else if(fields.length == 11){
				String a = fields[0];
				String b = fields[3];
				String c = fields[5];
				String d = fields[7];
				
				
				String time = df.format(new Date().toString());
				
				hkey.set(Bytes.toBytes(time + " ->" +a));
				
				kv = new KeyValue(hkey.get(), 
						Bytes.toBytes("data"), 
						Bytes.toBytes("a"),
						Bytes.toBytes(a));
				context.write(hkey, kv);
				
				kv = new KeyValue(hkey.get(), 
						Bytes.toBytes("data"), 
						Bytes.toBytes("b"),
						Bytes.toBytes(b));
				context.write(hkey, kv);
				
				kv = new KeyValue(hkey.get(), 
						Bytes.toBytes("data"), 
						Bytes.toBytes("c"),
						Bytes.toBytes(c));
				context.write(hkey, kv);
				
				kv = new KeyValue(hkey.get(), 
						Bytes.toBytes("data"), 
						Bytes.toBytes("d"),
						Bytes.toBytes(d));
				context.write(hkey, kv);
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
			return;
		}
		
		
		
	}
	
	
}
