## 脑裂是什么？Zookeeper是如何解决的？
    脑裂(split-brain)就是“大脑分裂”，也就是本来一个“大脑”被拆分了两个或多个“大脑”，
    我们都知道，如果一个人有多个大脑，并且相互独立的话，那么会导致人体“手舞足蹈”，“不听使唤”。
   
    脑裂通常会出现在集群环境中，比如ElasticSearch、Zookeeper集群，而这些集群环境有一个统一的特点，
    就是它们有一个大脑，比如ElasticSearch集群中有Master节点，Zookeeper集群中有Leader节点。
    Zookeeper集群中是不会出现脑裂问题的，而不会出现的原因就跟过半机制有关。过半机制中必须是大于
    
## zookeeper分布式锁避免羊群效应


## paxos协议，2PC，3PC和ZAB协议区别

## 选举策略