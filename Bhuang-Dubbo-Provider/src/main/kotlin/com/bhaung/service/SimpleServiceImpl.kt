package com.bhaung.service

import com.bhuang.api.SimpleService
import org.apache.dubbo.config.annotation.DubboService
import org.springframework.stereotype.Component

@DubboService
@Component
class SimpleServiceImpl: SimpleService {
    override fun sayHello(name: String): String {
        return "Hello $name"
    }
}