package com.bhuang.provider

import com.bhuang.api.GreetingService
import org.apache.dubbo.common.constants.CommonConstants
import org.apache.dubbo.config.ApplicationConfig
import org.apache.dubbo.config.ProtocolConfig
import org.apache.dubbo.config.RegistryConfig
import org.apache.dubbo.config.ServiceConfig
import java.util.concurrent.CountDownLatch

object ProviderApplication {
    private val zookeeperHost = System.getProperty("zookeeper.address", "127.0.0.1")
    private val zookeeperPort = System.getProperty("zookeeper.port", "2181")

    @JvmStatic
    fun main(args: Array<String>) {
        // -1，表示让Dubbo自动选择一个可用的端
        val protocolConfig = ProtocolConfig(CommonConstants.TRIPLE, -1).apply {
            // 这个属性用于指定扩展协议，这里设置为CommonConstants.DUBBO表示使用Dubbo协议作为扩展协议。
            extProtocol = CommonConstants.DUBBO
        }


        // 配置服务
        val service = ServiceConfig<GreetingService>().apply {
            application = ApplicationConfig("first-dubbo-provider")
            registry = RegistryConfig("zookeeper://$zookeeperHost:$zookeeperPort")
            setInterface(GreetingService::class.java)
            ref = GreetingServiceImpl()
            protocol = protocolConfig
        }

        // 导出服务
        service.export()

        println("dubbo service started")
        CountDownLatch(1).await()
    }
}
