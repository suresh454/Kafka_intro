### Start zookeeper
|Mac|
|:---|
|`bin/zookeeper-server-start.sh config/zookeeper.properties`|

### Start Kafka Server
|Mac|
|:---|
|`bin/kafka-server-start.sh config/server.properties`|

## Topic
### create a topic
|Mac|
|:---|
|`bin/kafka-topics.sh --zookeeper localhost:2181 --topic first_topic --create --partitions 3 --replication-factor 1`|

### describe a topic
|Mac|
|:---|
|`bin/kafka-topics.sh --zookeeper localhost:2181 --topic first_topic --describe`|

### list all topics
|Mac|
|:---|
|`bin/kafka-topics.sh --zookeeper localhost:2181 --list`|

### Console Producer
|Mac|
|:---|
|`bin/kafka-console-producer.sh --broker-list localhost:9092 --topic first_topic`|


### Console Consumer
|Mac|
|:---|
|`kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic first_topic --from-beginning`|



### Delete topics from Kafka
|Mac|
|:---|
|`kafka-topics.sh --zookeeper localhost:2181 --delete --topic first_topic`|

