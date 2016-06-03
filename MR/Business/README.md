
# YelpDataAnalysis-
Use hadoop to analysis yelp customer data

mvn clean

mvn package

 hadoop fs -mkdir -p /user/cloudera/TestMR/input/smallbusiness/1

 hadoop fs -put small.txt /user/cloudera/TestMR/input/smallbusiness/1

 hadoop fs -mkdir -p /user/cloudera/TestMR/output/smallbusiness/2

 hadoop jar target/Business-0.0.1-SNAPSHOT.jar yelp.business.BusinessMR /user/cloudera/TestMR/input/smallbusiness/1 /user/cloudera/TestMR/output/smallbusiness/2

 hadoop fs -cat /user/cloudera/TestMR/output/smallbusiness/2/*
 
 note: DB connection still does not work
