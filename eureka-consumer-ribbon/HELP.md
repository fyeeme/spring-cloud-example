# 简介

### 原理
 在 **eureka-consumer** 本项目通过 `LoadBalancerClient` 发现其他注册的服务，并通过 restTemplate 调用该服务，实现负载均衡。
 
 而本章，我们通过 修改 `RestTemplate` 的实例，来简化服务调用
 
这里我们通过增加了 `@LoadBalanced`
```java
@Bean
@LoadBalanced
public RestTemplate restTemplate() {
    return new RestTemplate();
}
```

```text
ServiceInstance serviceInstance = loadBalancerClient.choose("eureka-client");
String url = String.format("http://%s:%s/dc", serviceInstance.getHost(), serviceInstance.getPort());
log.info("{}", url);
```

```text
http://eureka-client/dc
```

调整后，我们在调用的地方不再关注具体的服务端口，而是通过 服务的注册名称来调用，是的服务更加透明