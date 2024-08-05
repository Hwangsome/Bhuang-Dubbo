package com.bhuang.controller

import com.bhuang.api.SimpleService
import org.apache.dubbo.config.annotation.DubboReference
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SimpleController {

    @DubboReference(protocol = "dubbo")
    private lateinit var dubboSimpleService: SimpleService

    @DubboReference(protocol = "tri")
    private lateinit var tripeSimpleService: SimpleService

    @GetMapping("/dubbo/hello")
    fun dubboHello(): String {
        return dubboSimpleService.sayHello("bhuang")
    }

    @GetMapping("/tripe/hello")
    fun triHello(): String {
        return tripeSimpleService.sayHello("bhuang")
    }
}