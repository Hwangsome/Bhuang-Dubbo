package com.bhuang.spi

class HttpProtocol: IProtocol {
    override fun sendRequest(message: String) {
        println("HttpProtocol sendRequest: $message")
    }
}