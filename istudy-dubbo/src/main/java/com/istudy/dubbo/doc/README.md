## SPI机制
> JDK SPI 和Dubbo SPI的区别：

    JDK SPI 会一次性实例化所有配置的实例：如果某些实例在程序中并不需要，那将会是极大的浪费。Dubbo SPI只会实例化需要的类。
    Dubbo SPI 还支持IOC（注入其他的实例）
    Dubbo SPI的灵活性更强，功能也更加强大。
