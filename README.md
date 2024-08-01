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