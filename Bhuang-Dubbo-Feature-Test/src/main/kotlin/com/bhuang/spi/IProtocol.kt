package com.bhuang.spi

import org.apache.dubbo.common.extension.ExtensionScope
import org.apache.dubbo.common.extension.SPI

/**
 * 通讯协议接口
 */
@SPI(scope = ExtensionScope.FRAMEWORK)
interface IProtocol {
    fun sendRequest(message: String)
}