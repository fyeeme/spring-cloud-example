# Getting Started

### Reference Documentation
For further reference, please consider the following sections:


### Guides
本章通过函数式展示了spring cloud stream rabbit 如何收发消息，
由于 `@EnableBinding`在 3.1.0之后的版本被废弃了，我们以新的方式来实现: 基于Bean的配置来指定消息的消费

1. 消息分组
当一个服务在生产环境有多个实例时，消费者会重复消费同一个消息，我可以通过指定消息分组来放置重复消费，配置如下

```properties
spring.cloud.stream.bindings.receive1-in-0.destination=customerId-1
#通过指定的 group 的 name, 将消息分组，同一个组的消费者只会被消费一次，这样可以解决在一个组内的消费者重复消费的问题
spring.cloud.stream.bindings.receive1-in-0.group=consumerGroup
```
我们只需要对 destination 在制定一个分组名称，这样多个实例在启动式就会只有个小消息队列，这样就可以解决重复消费的问题，
如上配置，如果我们没有配置消息分组，我们观察  (rabbit的控制台)[http://192.168.3.20:15672/#/queues] ，队列是这样的:

```
customerId-1.anonymous.01
customerId-1.anonymous.02
```
开启后是这样

```
customerId-1.consumerGroup
```
这样哪个实例先抢到了消息，就可以消费该消息

2. 消息分区

### Additional Links
These additional references should also help you:

* [spring-cloud-stream-samples](https://github.com/spring-cloud/spring-cloud-stream-samples)