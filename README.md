# pre-requisites
## zookeeper
### install
安装Zookeeper在Linux、Windows和Mac系统上有一些不同的步骤。以下是详细的安装步骤。

#### 在Linux上安装Zookeeper

1. **安装Java**：
    ```bash
    sudo apt update
    sudo apt install default-jre
    ```

2. **下载Zookeeper**：
    ```bash
    wget https://downloads.apache.org/zookeeper/zookeeper-3.7.1/apache-zookeeper-3.7.1-bin.tar.gz
    ```

3. **解压Zookeeper**：
    ```bash
    tar -xvzf apache-zookeeper-3.7.1-bin.tar.gz
    ```

4. **配置Zookeeper**：
    ```bash
    cd apache-zookeeper-3.7.1-bin
    cp conf/zoo_sample.cfg conf/zoo.cfg
    ```

5. **启动Zookeeper**：
    ```bash
    bin/zkServer.sh start
    ```

#### 在Windows上安装Zookeeper

1. **安装Java**：
    - 从Oracle或OpenJDK网站下载并安装Java。

2. **下载Zookeeper**：
    - 从[Apache Zookeeper官网](https://zookeeper.apache.org/releases.html)下载Zookeeper的二进制文件，并解压缩。

3. **配置Zookeeper**：
    - 进入解压缩后的目录，将`conf`文件夹中的`zoo_sample.cfg`复制并重命名为`zoo.cfg`。

4. **编辑配置文件**：
    - 使用文本编辑器打开`zoo.cfg`文件，并确保`dataDir`指向一个存在的目录，例如`dataDir=C:/zookeeper-data`。

5. **启动Zookeeper**：
    - 打开命令提示符，导航到Zookeeper的解压缩目录，运行以下命令：
      ```cmd
      bin\zkServer.cmd
      ```

#### 在Mac上安装Zookeeper

1. **安装Java**：
   ```bash
   brew install java
   ```

2. **下载并安装Zookeeper**：
   ```bash
   brew install zookeeper
   ```

3. **启动Zookeeper**：
   ```bash
   zkServer start
   ```

### 检查Zookeeper状态

启动后，可以通过以下命令检查Zookeeper的状态：

```bash
bin/zkServer.sh status  # Linux/Mac
bin\zkServer.cmd status # Windows
```

#### 关闭Zookeeper

关闭Zookeeper的命令如下：

```bash
bin/zkServer.sh stop  # Linux/Mac
bin\zkServer.cmd stop # Windows
```


# maven
```shell
mvn clean install -DskipTests
```

# provider
## zookeeper验证
当你启动服务提供者成功之后，可以去zookeeper的控制台查看是否有服务注册成功
可以看到provider 中的metadata信息 已经被注册到zookeeper中
```shell
zkCli.sh -server
ls /dubbo/com.bhuang.api.SimpleService/providers
[dubbo%3A%2F%2F10.0.29.28%3A20880%2Fcom.bhuang.api.SimpleService%3Fanyhost%3Dtrue%26application%3DBhuang-Dubbo-Provider%26background%3Dfalse%26deprecated%3Dfalse%26dubbo%3D2.0.2%26dynamic%3Dtrue%26executor-management-mode%3Ddefault%26file.cache%3Dtrue%26generic%3Dfalse%26interface%3Dcom.bhuang.api.SimpleService%26methods%3DsayHello%26pid%3D32974%26prefer.serialization%3Dfastjson2%2Chessian2%26release%3D3.2.0-beta.4%26service-name-mapping%3Dtrue%26side%3Dprovider%26timestamp%3D1722497090920]
```

# dubbo admin
## 1.0 环境
- 这个项目的要求是java 8
- zookeeper 需要先启动
## 1.1 Compile from source
1. Download code: `git clone https://github.com/apache/dubbo-admin.git`
2. Change `dubbo-admin-server/src/main/resources/application.properties` configuration to make Admin points to the designated registries, etc.
3. Build
   - `mvn clean package -Dmaven.test.skip=true`
4. Start
   * `mvn --projects dubbo-admin-server spring-boot:run`
     or
   * `cd dubbo-admin-distribution/target; java -jar dubbo-admin-${project.version}.jar`
5. Visit  `http://localhost:38080`, default username and password are `root`

# Dubbo 注册中心存储结构的分类
在 Dubbo 中，接口级注册和应用级注册是两种不同的服务注册方式，用于不同的场景和需求。

### 接口级注册
```shell
# 接口级注册的信息
[zk: localhost:2181(CONNECTED) 8] ls /dubbo/com.bhuang.api.SimpleService/providers 
[dubbo%3A%2F%2F172.20.10.6%3A20880%2Fcom.bhuang.api.SimpleService%3Fanyhost%3Dtrue%26application%3DBhuang-Dubbo-Provider%26background%3Dfalse%26deprecated%3Dfalse%26dubbo%3D2.0.2%26dynamic%3Dtrue%26executor-management-mode%3Ddefault%26file.cache%3Dtrue%26generic%3Dfalse%26interface%3Dcom.bhuang.api.SimpleService%26ipv6%3D2409%3A895a%3A327c%3Aeaab%3A24c2%3A8407%3A22d4%3A50c3%26methods%3DsayHello%26pid%3D24580%26prefer.serialization%3Dfastjson2%2Chessian2%26release%3D3.2.0-beta.4%26service-name-mapping%3Dtrue%26side%3Dprovider%26timestamp%3D1722955607339, tri%3A%2F%2F172.20.10.6%3A50051%2Fcom.bhuang.api.SimpleService%3Fanyhost%3Dtrue%26application%3DBhuang-Dubbo-Provider%26background%3Dfalse%26deprecated%3Dfalse%26dubbo%3D2.0.2%26dynamic%3Dtrue%26executor-management-mode%3Ddefault%26file.cache%3Dtrue%26generic%3Dfalse%26interface%3Dcom.bhuang.api.SimpleService%26ipv6%3D2409%3A895a%3A327c%3Aeaab%3A24c2%3A8407%3A22d4%3A50c3%26methods%3DsayHello%26pid%3D24580%26prefer.serialization%3Dfastjson2%2Chessian2%26release%3D3.2.0-beta.4%26service-name-mapping%3Dtrue%26side%3Dprovider%26timestamp%3D1722955607627]
# 应用级注册的信息
[zk: localhost:2181(CONNECTED) 9] ls /services/Bhuang-Dubbo-Provider 
[172.20.10.6:20880]
```
#### 定义
接口级注册是指服务的每个接口（即每个服务）都单独注册到注册中心。每个服务接口在注册中心中都有一个独立的条目，消费者可以单独订阅和调用这些接口。

#### 特点
- **细粒度控制**：每个接口单独注册和管理，便于精细化的控制和监控。
- **灵活性**：消费者可以选择性地订阅和调用特定的接口。
- **配置复杂**：需要对每个接口进行配置和注册，配置较为复杂。

#### 配置示例
在 Dubbo 配置文件中，接口级注册通常不需要特别的配置，只要定义好服务接口和实现，并进行服务注册即可。

```java
@DubboService
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "Hello, " + name;
    }
}
```

```yaml
dubbo:
  registry:
    address: zookeeper://127.0.0.1:2181
  protocols:
    dubbo:
      name: dubbo
      port: 20880

```
#### 接口级注册的应用场景
1. 细粒度服务管理
   - 需求分析：在大型企业级应用中，不同服务接口可能由不同的团队维护和部署，需要对每个接口进行单独的管理和监控。
   - 实现优势：接口级注册允许对每个服务接口进行独立的版本控制、负载均衡和访问控制，便于细粒度的管理和优化。

2. 精细化监控
   - 需求分析：在需要对每个接口的调用频次、响应时间、错误率等指标进行精细化监控的场景下，接口级注册可以提供更细致的数据。
   - 实现优势：每个接口都有独立的注册信息和监控数据，可以准确定位和解决问题。

3. 个性化配置
   - 需求分析：不同的服务接口可能需要不同的配置，比如超时时间、重试策略、流量控制等。
   - 实现优势：接口级注册允许对每个接口进行单独配置，满足个性化需求。

4. 选择性调用
   - 需求分析：消费者可能只需要订阅和调用某些特定的服务接口，而不需要访问整个应用的所有接口。
   - 实现优势：接口级注册可以让消费者选择性地订阅和调用所需的服务接口，避免不必要的资源消耗。


### 应用级注册

#### 定义
应用级注册是指整个应用作为一个单元注册到注册中心，所有的服务接口都通过一个应用条目进行注册和管理。消费者订阅的是整个应用，而不是单独的服务接口。

#### 特点
- **粗粒度控制**：整个应用作为一个单元进行管理，便于统一配置和管理。
- **简化配置**：只需要对应用进行注册，配置较为简单。
- **适用于微服务架构**：应用级注册通常用于微服务架构中，每个微服务作为一个独立的应用进行注册和管理。

#### 配置示例
在 Dubbo 配置文件中，可以通过设置 `scope` 参数为 `application` 来启用应用级注册。

```yaml
dubbo:
  application:
    name: demo-provider
    register-mode: interface
  registry:
    address: zookeeper://127.0.0.1:2181
  provider:
    register-mode: application
  protocols:
    dubbo:
      name: dubbo
      port: 20880
```

#### 应用级注册的应用场景
1. 微服务架构
   - 需求分析：在微服务架构中，每个微服务作为一个独立的应用进行部署和管理，服务间的通信通过统一的注册中心进行。
   - 实现优势：应用级注册可以简化微服务的注册和管理，将一个微服务的所有接口作为一个整体进行注册。
2. 简化配置和部署
   - 需求分析：对于中小型应用或单体应用，配置和管理所有服务接口的注册信息较为繁琐，且没有必要进行细粒度的管理。
   - 实现优势：应用级注册可以简化配置，只需对应用进行注册和管理，降低配置和部署的复杂度。
3. 一致性和统一管理
   - 需求分析：需要对应用内的所有服务接口进行统一的配置和管理，如统一的版本控制、负载均衡策略等。
   - 实现优势：应用级注册可以确保应用内的所有服务接口共享相同的配置和管理策略，保持一致性。
4. 快速扩展和缩减
   - 需求分析：在需要快速扩展或缩减服务实例的场景下，应用级注册可以简化实例的管理。
   - 实现优势：应用级注册可以通过统一的应用实例管理，实现快速的扩展和缩减，提高系统的弹性和可用性。
### 对比总结

| 特性             | 接口级注册                              | 应用级注册                              |
|------------------|-----------------------------------------|-----------------------------------------|
| 粒度             | 细粒度，单个接口                        | 粗粒度，整个应用                        |
| 灵活性           | 高，每个接口单独管理                    | 较低，整个应用作为一个单元管理          |
| 配置复杂度       | 高，需要对每个接口单独配置              | 低，只需配置应用                       |
| 场景适用性       | 适用于需要精细控制和监控的场景          | 适用于微服务架构，每个服务作为一个应用  |

选择哪种注册方式取决于具体的应用需求和架构设计。如果需要对每个服务接口进行精细化的控制和监控，可以选择接口级注册；如果需要简化配置和管理，且应用架构符合微服务模式，可以选择应用级注册。

# Dubbo 接口级注册中心存储结构
在 Dubbo 中，接口级注册中心的存储结构通常是通过 ZooKeeper 实现的。ZooKeeper 是一个分布式协调服务，用于配置管理、同步服务和命名注册。Dubbo 使用 ZooKeeper 来存储服务的注册信息和元数据。在接口级注册模式下，每个服务接口都在注册中心（如 ZooKeeper）中有一个独立的存储路径和结构。

### ZooKeeper 中的存储结构

以下是 Dubbo 在 ZooKeeper 中接口级注册的典型存储结构示例：

```plaintext
/dubbo
    └── com.example.MyService
        ├── providers
        │   ├── dubbo://192.168.1.100:20880/com.example.MyService?version=1.0.0&application=demo-provider
        │   └── dubbo://192.168.1.101:20880/com.example.MyService?version=1.0.0&application=demo-provider
        ├── consumers
        │   ├── consumer://192.168.1.102/com.example.MyService?version=1.0.0&application=demo-consumer
        ├── configurators
        └── routers
```

### 详细解释

- **/dubbo**:
   - Dubbo 在 ZooKeeper 中的根节点。

- **/dubbo/com.example.MyService**:
   - 服务接口 `com.example.MyService` 的节点，每个服务接口都有一个独立的节点。

- **/dubbo/com.example.MyService/providers**:
   - 该节点存储了所有提供 `com.example.MyService` 服务的提供者信息。每个提供者的 URL 包含了服务的详细信息，如协议、主机、端口、版本、应用名等。

- **/dubbo/com.example.MyService/consumers**:
   - 该节点存储了所有消费 `com.example.MyService` 服务的消费者信息。每个消费者的 URL 包含了消费者的详细信息，如协议、主机、版本、应用名等。

- **/dubbo/com.example.MyService/configurators**:
   - 该节点存储了服务的动态配置，如流量控制、负载均衡策略等。

- **/dubbo/com.example.MyService/routers**:
   - 该节点存储了服务的路由规则，用于服务的访问控制和路由选择。

### 存储示例

假设我们有一个服务接口 `com.example.MyService`，由两个提供者和一个消费者组成，其在 ZooKeeper 中的存储结构如下：

```plaintext
/dubbo
    └── com.example.MyService
        ├── providers
        │   ├── dubbo://192.168.1.100:20880/com.example.MyService?version=1.0.0&application=demo-provider
        │   └── dubbo://192.168.1.101:20880/com.example.MyService?version=1.0.0&application=demo-provider
        ├── consumers
        │   ├── consumer://192.168.1.102/com.example.MyService?version=1.0.0&application=demo-consumer
        ├── configurators
        └── routers
```

### 存储信息说明

- **提供者信息**:
   - `dubbo://192.168.1.100:20880/com.example.MyService?version=1.0.0&application=demo-provider`：
      - 表示一个提供 `com.example.MyService` 服务的提供者，运行在 `192.168.1.100:20880`，服务版本为 `1.0.0`，应用名为 `demo-provider`。
   - `dubbo://192.168.1.101:20880/com.example.MyService?version=1.0.0&application=demo-provider`：
      - 表示另一个提供 `com.example.MyService` 服务的提供者，运行在 `192.168.1.101:20880`，服务版本为 `1.0.0`，应用名为 `demo-provider`。

- **消费者信息**:
   - `consumer://192.168.1.102/com.example.MyService?version=1.0.0&application=demo-consumer`：
      - 表示一个消费 `com.example.MyService` 服务的消费者，运行在 `192.168.1.102`，服务版本为 `1.0.0`，应用名为 `demo-consumer`。

### 优势

接口级注册的优点在于：
- **精细化管理**：每个服务接口都有独立的注册信息，便于精细化的管理和监控。
- **灵活性高**：消费者可以选择性地订阅和调用特定的服务接口。
- **独立配置**：每个服务接口可以独立配置，例如不同的负载均衡策略、重试策略等。

通过这种结构，Dubbo 可以高效地管理和调度服务接口，确保服务的高可用性和性能。

# 接口级注册中心一个应用的情况
在接口级注册中，不同服务接口的多个实例、一个服务接口的多个方法、以及多个服务接口的注册，都会在注册中心（如 ZooKeeper）中有其特定的存储结构和处理方式。下面是详细的说明和注意事项：

### 1. 多个实例，Provider 中对应多个 URL 记录

在一个应用中，如果一个服务接口有多个实例，每个实例都会在注册中心注册一个 URL。这些 URL 记录包含了每个实例的详细信息，如 IP 地址、端口号等。消费者在调用服务时，可以根据这些 URL 进行负载均衡和故障转移。

#### 示例
假设有一个服务接口 `com.example.MyService`，该服务接口有两个实例，它们在注册中心的存储结构如下：

```plaintext
/dubbo
    └── com.example.MyService
        ├── providers
        │   ├── dubbo://192.168.1.100:20880/com.example.MyService?version=1.0.0&application=demo-provider
        │   └── dubbo://192.168.1.101:20880/com.example.MyService?version=1.0.0&application=demo-provider
```

### 2. 一个 Service 中多个方法，Provider 中对应 1 个 URL 记录

在 Dubbo 中，一个服务接口的多个方法共享同一个 URL。也就是说，Dubbo 不会为每个方法单独创建 URL 记录，而是将整个服务接口作为一个单元进行注册。消费者可以通过这个 URL 访问服务接口中的所有方法。

#### 示例
假设 `com.example.MyService` 接口有多个方法：

```java
public interface SimpleService {
    String sayHello(String name);
    String sayHi(String name);
}
```

它们在注册中心的存储结构如下：
可以看到一个URL 中包含了这个服务的两个方法的名字
```plaintext
/dubbo
    └── com.example.MyService
        ├── providers
        │   ├── dubbo://172.20.10.6:20880/com.bhuang.api.SimpleService?anyhost=true&application=Bhuang-Dubbo-Provider&background=false&bind.ip=172.20.10.6&bind.port=20880&deprecated=false&dubbo=2.0.2&dynamic=true&executor-management-mode=default&file.cache=true&generic=false&interface=com.bhuang.api.SimpleService&ipv6=2409:895a:327c:eaab:24c2:8407:22d4:50c3&methods=sayHello,sayHi&pid=39201&prefer.serialization=fastjson2,hessian2&qos.enable=true&release=3.2.0-beta.4&service-name-mapping=true&side=provider&timestamp=1722958227333
```

### 3. 多个服务接口，Dubbo 为每一个服务接口创建对应的节点，并在节点下创建 Provider 节点

在接口级注册中，每个服务接口在注册中心都有一个独立的节点。每个服务接口的节点下包含 `providers`、`consumers`、`configurators` 和 `routers` 等子节点，用于存储该服务接口的提供者信息、消费者信息、配置和路由规则。

#### 示例
假设有两个服务接口 `com.example.MyService` 和 `com.example.AnotherService`，它们在注册中心的存储结构如下：

```plaintext
/dubbo
    ├── com.example.MyService
    │   ├── providers
    │   │   ├── dubbo://192.168.1.100:20880/com.example.MyService?version=1.0.0&application=demo-provider
    │   │   └── dubbo://192.168.1.101:20880/com.example.MyService?version=1.0.0&application=demo-provider
    │   ├── consumers
    │   ├── configurators
    │   └── routers
    └── com.example.AnotherService
        ├── providers
        │   ├── dubbo://192.168.1.100:20881/com.example.AnotherService?version=1.0.0&application=demo-provider
        │   └── dubbo://192.168.1.101:20881/com.example.AnotherService?version=1.0.0&application=demo-provider
        ├── consumers
        ├── configurators
        └── routers
```

### 注意事项

1. **注册中心一致性**：确保所有服务提供者和消费者使用同一个注册中心地址。
2. **服务版本管理**：为每个服务接口指定版本号，便于服务升级和兼容管理。
3. **网络配置**：确保服务提供者的 IP 地址和端口在消费者可访问的网络范围内，避免使用 `localhost` 或 `127.0.0.1`。
4. **负载均衡和故障转移**：Dubbo 会自动根据注册中心的 URL 记录进行负载均衡和故障转移，确保高可用性。
5. **监控和管理**：使用 Dubbo Admin 等工具对注册中心中的服务进行监控和管理，及时发现和解决问题。

通过这些配置和管理，您可以确保 Dubbo 服务的高可用性和可维护性，实现高效的服务调用和管理。