ADD JAR /usr/lib/hive/lib/hive-serdes-1.0-SNAPSHOT.jar;

CREATE EXTERNAL TABLE Yelp_Review(
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
ROW FORMAT SERDE 'com.cloudera.hive.serde.JSONSerDe'
LOCATION
'/home/cloudera/YelpData/data/review';

create table Yelp_Review_tmp as select * from Yelp_Review;