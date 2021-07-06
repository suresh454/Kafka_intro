### Start zookeeper
`bin/zookeeper-server-start.sh config/zookeeper.properties`

### Start Kafka Server
`bin/kafka-server-start.sh config/server.properties`

## Topic
### create a topic
`bin/kafka-topics --zookeeper localhost:2181 --topic first_topic --create --partitions 3 --replication-factor 1`

### describe a topic
`bin/kafka-topics.sh --zookeeper localhost:2181 --topic first_topic --describe`

### list all topics
`bin/kafka-topics.sh --zookeeper localhost:2181 --list`

### Console Producer
`bin/kafka-console-producer --broker-list localhost:9092 --topic first_topic`


### Console Consumer
`kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic first_topic --from-beginning`



### Delete topics from Kafka
`kafka-topics.sh --zookeeper localhost:2181 --delete --topic first_topic`

