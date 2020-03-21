## Spring Cloud Feign 原理步骤
    a.通过主类上的EnableFeignClients 注解开启FeignClient；
    b.根据Feign 的规则实现接口，并加上FeignClient注解，供调用的地方注入调用；
    c.程序启动后，会扫描所有FeignClient 注解的类，并将这些信息注入到IOC 容器中；
    d.当b中接口被调用时，通过jdk代理，以及反射（Spring处理注解的方式），来生成具体的RequestTemplate
    e.RequestTemplate 生成Reqest
    f.Request 交给httpclient处理，这里的httpclient 可以是OkHttp，也可以是HttpUrlConnection 或者HttpClient
    g.最后Client被封装到LoadBalanceClient类，这个类结合Ribbon 实现负载均衡