package com.bhuang.controller

import com.bhuang.api.SimpleService
import org.apache.dubbo.config.annotation.DubboReference
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SimpleController {

    @DubboReference
    private lateinit var simpleService: SimpleService

    @GetMapping("/hello")
    fun hello(): String {
        return simpleService.sayHello("bhuang")
    }
}