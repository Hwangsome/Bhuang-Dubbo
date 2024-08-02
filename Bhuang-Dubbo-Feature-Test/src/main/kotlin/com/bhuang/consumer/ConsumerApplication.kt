package com.bhuang.consumer

import com.bhuang.api.GreetingService
import org.apache.dubbo.common.constants.CommonConstants
import org.apache.dubbo.config.ApplicationConfig
import org.apache.dubbo.config.ReferenceConfig
import org.apache.dubbo.config.RegistryConfig
import kotlin.concurrent.thread

object ConsumerApplication {
    private val zookeeperHost = System.getProperty("zookeeper.address", "127.0.0.1")
    private val zookeeperPort = System.getProperty("zookeeper.port", "2181")

    @JvmStatic
    fun main(args: Array<String>) {
        val dubboClient = Client(CommonConstants.DUBBO)
        val triClient = Client(CommonConstants.TRIPLE, "FORCE_INTERFACE")

        val t1 = thread { dubboClient.run() }
        val t2 = thread { triClient.run() }

        t1.join()
        t2.join()
    }

    class Client(private val protocol: String, private val mode: String = "APPLICATION_FIRST") : Runnable {

        override fun run() {
            val reference = ReferenceConfig<GreetingService>().apply {
                setInterface(GreetingService::class.java)
                parameters = HashMap<String, String>().apply {
                    put("migration.step", mode)
                }
                application = ApplicationConfig("first-dubbo-consumer")
                registry = RegistryConfig("zookeeper://$zookeeperHost:$zookeeperPort")
                this.protocol = this@Client.protocol
            }
            val service = reference.get()
            val message = service.sayHi(this.protocol)
            println(message)
        }
    }
}