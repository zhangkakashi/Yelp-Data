ADD JAR /usr/lib/hive/lib/hive-serdes-1.0-SNAPSHOT.jar;

CREATE EXTERNAL TABLE Yelp_User(
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
ROW FORMAT SERDE 'com.cloudera.hive.serde.JSONSerDe'
LOCATION
'/home/cloudera/YelpData/data/user';

create table Yelp_User_tmp as select * from Yelp_User;