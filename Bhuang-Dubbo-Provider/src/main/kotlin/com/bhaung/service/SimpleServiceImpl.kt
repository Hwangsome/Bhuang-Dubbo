package com.bhaung.service

import com.bhuang.api.SimpleService
import org.apache.dubbo.config.annotation.DubboService
import org.springframework.stereotype.Component

/**
 * 声明需要暴露的服务接口
 */
@DubboService
class SimpleServiceImpl: SimpleService {
    override fun sayHello(name: String): String {
        return "Hello $name"
    }
}