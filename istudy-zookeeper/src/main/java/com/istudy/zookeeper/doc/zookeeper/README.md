## 脑裂是什么？Zookeeper是如何解决的？
    脑裂(split-brain)就是“大脑分裂”，也就是本来一个“大脑”被拆分了两个或多个“大脑”，
    我们都知道，如果一个人有多个大脑，并且相互独立的话，那么会导致人体“手舞足蹈”，“不听使唤”。
   
    脑裂通常会出现在集群环境中，比如ElasticSearch、Zookeeper集群，而这些集群环境有一个统一的特点，
    就是它们有一个大脑，比如ElasticSearch集群中有Master节点，Zookeeper集群中有Leader节点。
    Zookeeper集群中是不会出现脑裂问题的，而不会出现的原因就跟过半机制有关。过半机制中必须是大于
    
## Zookeeper通过ZAB保证分布式事务的最终一致性。
    ZAB全称Zookeeper Atomic Broadcast（ZAB，Zookeeper原子消息广播协议）
    1.ZAB是一种专门为Zookeeper设计的一种支持 崩溃恢复 的 原子广播协议 ，是Zookeeper保证
    数据一致性的核心算法。ZAB借鉴了Paxos算法，但它不是通用的一致性算法，是特别为Zookeeper设计的。
    
    2.基于ZAB协议，Zookeeper实现了⼀种主备模式的系统架构来保持集群中各副本之间的数据的⼀致性，
    表现形式就是使⽤⼀个单⼀的主进程（Leader服务器）来接收并处理客户端的所有事务请求（写请求），
    并采⽤ZAB的原⼦⼴播协议，将服务器数据的状态变更为事务Proposal的形式⼴播到所有的Follower进程中。
    
    
## zookeeper分布式锁避免羊群效应


## paxos协议，2PC，3PC和ZAB协议区别

## 选举策略