CREATE TABLE Yelp_Review_HBase(
    type STRING,
    business_id STRING,
    user_id STRING,
    stars INT,
    text STRING,
    date STRING,
    votes STRUCT<
                 useful:INT,  
                 funny:INT, 
                 cool:INT 
                >
)
STORED BY 
'org.apache.hadoop.hive.hbase.HBaseStorageHandler' 
WITH SERDEPROPERTIES
("hbase.columns.mapping" = "review:type, :key, review:user_id, review:stars, review:text, review:date, votes:votes")
TBLPROPERTIES 
("hbase.table.name" = "YELP_REVIEW");

INSERT OVERWRITE TABLE Yelp_Review_HBase SELECT * FROM Yelp_Review_tmp;