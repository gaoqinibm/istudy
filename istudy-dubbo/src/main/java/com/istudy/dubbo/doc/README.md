## SPI机制
> JDK SPI和Dubbo SPI的区别：

    JDK SPI会一次性实例化所有配置的实例：如果某些实例在程序中并不需要，那将会是极大的浪费。Dubbo SPI只会实例化需要的类。
    Dubbo SPI还支持IOC（注入其他的实例）
    Dubbo SPI的灵活性更强，功能也更加强大。

    dubbo在原有的spi基础上主要有以下的改变,
    1.配置文件采用键值对配置的方式，使用起来更加灵活和简单 
    2.增强了原本SPI的功能，使得SPI具备ioc和aop的功能，这在原本的java中spi是不支持的。
    dubbo的spi是通过ExtensionLoader来解析的，通过ExtensionLoader来加载指定的实现类，配置文件的路径在META-INF/dubbo路径下

    jdk SPI使用ServiceLoader.load()
    Dubbo SPI使用ExtensionLoader解析

> dubbo注册源码分析
> 负载均衡
> 序列化

## Dubbo的底层实现原理和机制
    Dubbo是一个RPC框架，SOA框架
    Dubbo缺省协议采用单一长连接和NIO异步通讯，适合于小数据量大并发的服务调用，以及服务消费者机器数远大于服务提供者机器数的情况。
    
    作为RPC：支持各种传输协议，如dubbo,hession,json,fastjson，底层采用mina,netty长连接进行传输！典型的provider和cusomer模式
    作为SOA：具有服务治理功能，提供服务的注册和发现！用zookeeper实现注册中心！启动时候服务端会把所有接口注册到注册中心，
    并且订阅configurators,服务消费端订阅provide，configurators,routers,订阅变更时，zk会推送providers,configuators，routers,
    启动时注册长连接，进行通讯！proveider和cusomer启动后，后台启动定时器，发送统计数据到monitor（监控中心）！
    提供各种容错机制和负载均衡策略！
    