package com.bhaung.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


/**
 * InfoController
 * 用来检测服务是否正常启动
 */
@RestController
class InfoController {

    @GetMapping("/info")
    fun info(): String {
        return "Hello, this is a simple Spring Boot application."
    }
}