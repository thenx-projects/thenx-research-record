# Kafka 消息发送

### 创建 Topiic
`$ kafka-topics.sh --zookeeper localhost:2181 --create --topic topic02 --partitions 2 --replication-factor`

### 查询 topic
` $ kafka-topics.sh --zookeeper localhost:2181 --list `

### 查询 group消费者组中的消息
` $ kafka-consumer-groups.sh --bootstrap-server localhost:9092 --describe --group group02 `