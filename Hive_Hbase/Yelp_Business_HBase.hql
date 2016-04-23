CREATE TABLE Yelp_Business_HBase(
    type STRING,
    business_id STRING,
    name STRING,
    neighborhoods ARRAY<STRING>,
    full_address STRING,
    city STRING,
    state STRING,
    latitude DOUBLE,
    longitude DOUBLE,
    stars DOUBLE,
    review_count INT,
    categories array<STRING>,
    open BOOLEAN
)
STORED BY 
'org.apache.hadoop.hive.hbase.HBaseStorageHandler' 
WITH SERDEPROPERTIES
("hbase.columns.mapping" = "business:type, :key, business:name, neighborhoods:neighborhoods, business:full_address, 
business:city, business:state, business:latitude, business:longitude, business:stars, 
business:review_count, categories:categories, business:open")
TBLPROPERTIES 
("hbase.table.name" = "YELP_BUSINESS");

INSERT OVERWRITE TABLE Yelp_Business_HBase SELECT * FROM Yelp_Business_tmp;