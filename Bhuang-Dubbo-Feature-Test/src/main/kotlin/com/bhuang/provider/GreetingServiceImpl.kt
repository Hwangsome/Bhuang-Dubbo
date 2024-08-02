package com.bhuang.provider

import com.bhuang.api.GreetingService

class GreetingServiceImpl: GreetingService {
    override fun sayHi(name: String): String {
        return "hi, $name";
    }
}