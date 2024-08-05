package com.bhaung.service

import com.bhuang.api.SimpleService
import org.apache.dubbo.config.annotation.DubboService

/**
 * 声明需要暴露的服务接口
 * 表示SimpleService 仅仅支持dubbo和tri协议
 * 如果这里不指定， 默认支持yaml配置文件中配置的所有协议
 *
 * protocol = ["dubbo", "tri"] 中的值需要和yaml配置文件中的协议名称一致
 * yaml中的配置是这样的：dubbo.protocols.dubbo.name=dubbo dubbo.protocols.tri.name=tri
 * 所以这里的值可以是："dubbo", "tri"
 */
@DubboService(protocol = ["dubbo", "tri"])
class SimpleServiceImpl: SimpleService {
    override fun sayHello(name: String): String {
        return "Hello $name"
    }
}