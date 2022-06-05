# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

### Guides
The following guides illustrate how to use some features concretely:

我们通过刚刚的 `config-server` 服务来访问配置信息， 首先我们需要 创建 `bootstrap.yml` 文件，内容如下

```yml
spring:
  application:
    # 对应 上文中的 application
    name: config-client
  cloud:
    config:
      # config-server
      uri: http://localhost:8100/
      # profile
      profile: default
      # label
      label: main

server:
  port: 8101

```
即对应如下解析
/{application}/{profile}[/{label}]

### Additional Links
These additional references should also help you:


