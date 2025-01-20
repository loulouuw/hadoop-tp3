### Lancement et connexion au container
```
docker run --rm \
  -p 8088:8088 \
  -p 9870:9870 \
  -p 9864:9864 \
  -v "/mnt/c/Users/jerom/Documents/hadoop-tp3/data:/data" \
  -v "/mnt/c/Users/jerom/Documents/hadoop-tp3/jars:/jars" \
  --name hadoop-container \
  hadoop-tp3

docker exec -it hadoop-container bash
```
### Jobs :

Job 1 :
```	
hdfs dfs -mkdir -p /input/job1
hdfs dfs -put /data/relationships/data.txt /input/job1/

hadoop jar /jars/tpfinal-Louis_RIDEAU_job1.jar /input/job1/data.txt /output/job1

hdfs dfs -cat /output/job1/part-r-00000
hdfs dfs -cat /output/job1/part-r-00001
```
Job 2 : 
```
hadoop jar /jars/tpfinal-Louis_RIDEAU_job2.jar /output/job1 /output/job2

hdfs dfs -cat /output/job2/part-r-00000
```
Job 3 :
```
hadoop jar /jars/tpfinal-Louis_RIDEAU_job3.jar /output/job2 /output/job3

hdfs dfs -cat /output/job3/part-r-00000
```
