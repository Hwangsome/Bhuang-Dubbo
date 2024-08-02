package com.bhuang.spi

import org.apache.dubbo.common.extension.SPI

/**
 * 通讯协议接口
 */
@SPI
interface IProtocol {
    fun sendRequest(message: String)
}