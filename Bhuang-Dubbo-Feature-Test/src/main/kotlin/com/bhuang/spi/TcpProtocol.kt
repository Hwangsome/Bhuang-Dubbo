package com.bhuang.spi

class TcpProtocol: IProtocol {
    override fun sendRequest(message: String) {
        println("TcpProtocol sendRequest: $message")
    }
}