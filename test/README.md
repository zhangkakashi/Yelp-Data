mvn clean

mvn package

 hadoop fs -mkdir -p /user/cloudera/TestMR/input/test/1

 hadoop fs -put input.data /user/cloudera/TestMR/input/test/1
 
 hadoop fs -mkdir -p /user/cloudera/TestMR/output/test/1

 hadoop jar target/Business-0.0.1-SNAPSHOT.jar yelp.business.BusinessMR /user/cloudera/TestMR/input/smallbusiness/1 /user/cloudera/TestMR/output/smallbusiness/2

 hadoop fs -cat /user/cloudera/TestMR/output/smallbusiness/2/*