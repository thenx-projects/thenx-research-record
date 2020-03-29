# Kafka 消息发送

## 启动Kafka
kafka 启动分为 zookeeper server和 kafka server

### 启动 zookeeper
在 kafka 的根目录中键入如下命令：
` $ bin/zookeeper-server-start.sh config/zookeeper.properties `

### 启动 kafka
在 kafka 的根目录中键入如下命令：
` $ bin/kafka-server-start.sh config/server.properties`

## kafka基本操作
### 创建 Topiic
`$ kafka-topics.sh --zookeeper localhost:2181 --create --topic topic02 --partitions 2 --replication-factor`

### 查询 topic
` $ kafka-topics.sh --zookeeper localhost:2181 --list `

### 查询 group消费者组中的消息
` $ kafka.sh --bootstrap-server localhost:9092 --describe --group group02 `-consumer-groups