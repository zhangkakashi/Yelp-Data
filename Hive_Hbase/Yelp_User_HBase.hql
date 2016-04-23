CREATE TABLE Yelp_User_HBase(
    type STRING,
    user_id STRING,
    name STRING, 
    review_count INT,
    average_stars DOUBLE,
    votes STRUCT<
                 useful:INT,  
                 funny:INT, 
                 cool:INT 
                >
)
STORED BY 
'org.apache.hadoop.hive.hbase.HBaseStorageHandler' 
WITH SERDEPROPERTIES
("hbase.columns.mapping" = "user:type, :key, user:name, user:review_count, user:average_stars, votes:votes")
TBLPROPERTIES 
("hbase.table.name" = "YELP_USER");

INSERT OVERWRITE TABLE Yelp_User_HBase SELECT * FROM Yelp_User_tmp;

