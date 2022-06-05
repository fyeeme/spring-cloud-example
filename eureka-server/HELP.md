# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.5.9/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.5.9/gradle-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.5.9/reference/htmlsingle/#boot-features-developing-web-applications)
* [Eureka Server](https://docs.spring.io/spring-cloud-netflix/docs/current/reference/html/#spring-cloud-eureka-server)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.5.9/reference/htmlsingle/#using-boot-devtools)

### Guides

在Eureka的使用过程中，有两个延迟是需要注意的。

- 对于服务注册，启动Eureka客户端，它不会马上注册到Eureka服务器。在默认情况下，启动后需要等上40秒后，才会发送REST风格请求到Eureka服务器请求注册。如果注册不成功，它会每30秒尝试注册一次。换句话说，并不是启动Eureka客户端之后，它就马上注册，这是需要注意的地方。
- 对于服务发现，客户端存在自己的缓存清单，在默认的情况下，它是30秒维护一次。换句话说，即使你的新微服务注册到了Eureka，该缓存清单也可能不包含这个新微服务，只有当缓存清单刷新后才能发现新注册的微服务，这是大家在实践中需要注意的。

### Additional Links

These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

