## Canal原理
    Canal Server模拟MySQL Slave的交互协议，伪装自己为MySQL的Slave向Master发送dump协议，
    Master收到请求后开始推送binary log，Canal解析byte流产出解析后的增量数据。主要优点是流程架构非常清晰，
    部署和配置等相对简单，同时可以额外做一些配置管理、开发改造的工作。Canal的主要缺点是Server中的Instance
    和Client之间是一对一的消费，不太适用于多消费和数据分发的场景。