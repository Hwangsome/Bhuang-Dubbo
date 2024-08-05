package com.bhaung.service

import com.bhuang.api.SimpleService
import org.apache.dubbo.config.annotation.DubboReference
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

/**
 * This is a test class to test the multi-protocol feature.
 * 测试Dubbo多协议特性
 */
@SpringBootTest
class MultiProtocol {

    @DubboReference(protocol = "dubbo")
    private lateinit var dubboSimpleService: SimpleService

    @DubboReference(protocol = "rest")
    private lateinit var restSimpleService: SimpleService

    @Test
    fun dubboHello() {
        println(dubboSimpleService.sayHello("bhuang"))
    }

    @Test
    fun restHello() {
        println(restSimpleService.sayHello("bhuang"))
    }
}