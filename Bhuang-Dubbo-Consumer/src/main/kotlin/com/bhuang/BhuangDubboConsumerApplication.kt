package com.bhuang

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableDubbo
class BhuangDubboConsumerApplication

fun main(args: Array<String>) {
    runApplication<BhuangDubboConsumerApplication>(*args)
}
