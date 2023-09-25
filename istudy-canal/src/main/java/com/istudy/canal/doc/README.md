## Canal原理
    Canal Server模拟MySQL Slave的交互协议，伪装自己为MySQL的Slave向Master发送dump协议，
    Master收到请求后开始推送binary log，Canal解析byte流产出解析后的增量数据。主要优点是流程架构非常清晰，
    部署和配置等相对简单，同时可以额外做一些配置管理、开发改造的工作。Canal的主要缺点是Server中的Instance
    和Client之间是一对一的消费，不太适用于多消费和数据分发的场景。
    
## Canal常见问题与解决方法
    canal和Kafka两个配置,当消息体大小超出了canal端配置maxRequestSize的配置后,错误日志里会明确消息体大小,
    调整Kafka和Canal的消息体大小，类似"The message is 17853226 bytes when serialized which is larger 
    than the maximum request size you have configured with the max.request.size configuration.",
    如果消息体大小超出了canal端的配置且超出了Kafka端的限制,就只会输出
    "The request included a message larger than the max message size the server will accept"
    
    解决方式：
    #canal相关配置
    canal.mq.maxRequestSize = 104857600  #调大可接收消息体大小
    canal.mq.compressionType = lz4  #开启消息体压缩功能

## canal二次开发
    canal实现mysql数据同步到redis
