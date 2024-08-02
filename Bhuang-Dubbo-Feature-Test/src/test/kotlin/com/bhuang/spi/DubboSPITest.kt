package com.bhuang.spi

import org.apache.dubbo.common.extension.ExtensionLoader
import org.junit.jupiter.api.Test

class DubboSPITest {
    @Test
    fun test() {
        //获取ExtensionLoader对象
        val extensionLoader = ExtensionLoader.getExtensionLoader(IProtocol::class.java)
        //通过key获取对象的TcpProtocol
        val tcpProtocol = extensionLoader.getExtension("tcp")
        tcpProtocol.sendRequest("Hello!")
        //通过key获取对象的HttpProtocol
        val httpProtocol = extensionLoader.getExtension("http")
        httpProtocol.sendRequest("Hello!")
    }
}