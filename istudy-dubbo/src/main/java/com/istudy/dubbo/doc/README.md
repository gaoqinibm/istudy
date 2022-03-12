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