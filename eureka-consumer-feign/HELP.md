# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

### Guides

1. 启用 open feign
本章我们通过 `Spring cloud Feign` 替代了一篇的 `Spring cloud Ribbon` 的调用方式，不再依赖 `RestTemplate`，


我们将 

```java
restTemplate.getForObject("http://eureka-client/dc", String.class)
```
改为

```java
  @GetMapping("/consumer")
    public String consumer() {
        return dcClient.consumer();
    }
```
我们将服务的依赖通过声明`dcClient`来实现。
这样，我们可以通过声明式服务调用其他的依赖服务。它使得编写Web服务更加简单。


2. 启用熔断器

在 3.0.1之前的版本，我们使用 `Hystrix` 来实现熔断器，配置如下

```properties
feign.hystrix.enabled=true
```

```groovy
 implementation 'org.springframework.cloud:spring-cloud-netflix-hystrix'
```

剩下的就是对 open feign 接口实现一个默认的fallback, 这里并未改变，就不再详细说明。
我们主要讲一下 3.0.1之后的版本配置和依赖的改变


```properties
feign.circuitbreaker.enabled=true
```

```groovy
implementation 'org.springframework.cloud:spring-cloud-starter-circuitbreaker-resilience4j'
```

这样，我们就可以使用 `Resilience4J` 来实现熔断器。



- spring-cloud-sleuth-zipkin
  为了演示分布式链路调用追踪， 我们在 引入了

```groovy
implementation 'org.springframework.cloud:spring-cloud-starter-sleuth'
implementation 'org.springframework.cloud:spring-cloud-sleuth-zipkin'
```

这样在调用链就会有跟踪ID,我们可以通过 启动 zipkin-server 查询追踪ID

1. 启动 zipkin-server
   
```shell
docker run -d -p 9411:9411 openzipkin/zipkin
```
2. 打开浏览器，访问 http://localhost:9411/zipkin


### Additional Links
These additional references should also help you:

* [spring-cloud-feign-circuitbreaker](https://docs.spring.io/spring-cloud-openfeign/docs/current/reference/html/#spring-cloud-feign-circuitbreaker)
* [zipkin](https://github.com/openzipkin/zipkin)



