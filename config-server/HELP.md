# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [spring cloud config](https://spring.io/projects/spring-cloud-config)

### Guides

通过 `spring.cloud.config.server.git.uri` 我们指定了一个 git 地址，这个地址是我们的配置文件

如果我们的git仓库需要权限访问，我们可以通过配置一下属性：

```properties
spring.cloud.config.server.git.username：访问Git仓库的用户名
spring.cloud.config.server.git.password：访问Git仓库的用户密码
```

启动改工程后，可以通过一下规则访问

/{application}/{profile}[/{label}]
/{application}-{profile}.yml
/{label}/{application}-{profile}.yml
/{application}-{profile}.properties
/{label}/{application}-{profile}.properties

以我们自己的git 配置为例
地址: https://github.com/fyeeme/spring-cloud-config
分支: main

有两个配置
```text
config-client.yml
config-client-dev.yml
```

http://localhost:1201/config-client/dev/main 这样就可以访问到
`main` 分支下的 `config-client-dev.yml`

### 配置加密

- JCE

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

