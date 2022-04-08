## Sentinel 简介
    是阿里巴巴开源的一款断路器实现，目前在Spring Cloud的孵化器项目Spring Cloud Alibaba中，预计Spring Cloud H系列中可以孵化完成。
    尽管Sentinel尚未在Spring Cloud项目中孵化完成，但Sentinel本身在阿里内部已经被大规模采用，非常稳定。因此可以作为一个较好的替代品。
![Alt text](../sentinel/比较.png)

## 常见的限流方式
    限制总并发数（如数据库连接池、线程池）、
    限制瞬时并发数（nginx的limit_conn模块，用来限制瞬时并发连接数）、
    限制时间窗口内的平均速率（如Guava的RateLimiter、nginx的limit_req模块，限制每秒的平均速率）；
    其他的还有限制远程接口调用速率、限制MQ的消费速率。另外还可以根据网络连接数、网络流量、CPU或内存负载等来限流。