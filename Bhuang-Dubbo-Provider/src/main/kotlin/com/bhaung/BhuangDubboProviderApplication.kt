package com.bhaung

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableDubbo
class BhuangDubboProviderApplication

fun main(args: Array<String>) {
	runApplication<BhuangDubboProviderApplication>(*args)
}
