## spark数据模型

## 编程模型和作业调度

## RDD 介绍
弹性分布式数据集 Resilient Distributed Datasets，是MapReduce模型的扩展和延伸。

## OLAP和OLTP
* OLAP：On-Line Analytical Processing，联机分析处理。OLAP是数据仓库系统的主要应用，
支持复杂的分析操作，侧重决策支持，并且提供直观易懂的查询结果。通俗的讲，就是对数据按不同维度的聚合，维度的上钻，下卷等

* OLTP：On-line Transaction Processing，联机事务处理。传统的关系型数据库的主要应用，主要是基本的、日常的事务处理，
例如银行交易。通俗的讲，就是对数据的增删改查等操作

## spark逻辑执行流程

## Spark LRU算法的实现及讨论
    LRU替换策略的确切含义是优先替换掉当前最久未被使用的RDD。 如果查看Spark的源码，则会发现好像没有相关的LRU算法实现代码。
    实际上，Spark直接利用了7.2.4节中介绍的LinkedHashMap自带的LRU功能实现了缓存替换。LinkedHashMap使用双向链表实现，
    每当Spark插入或读取其中的RDD分区数据时，LinkedHashMap自动调整链表结构，将最近插入或者最近被读取的分区数据放在表头，
    这样链表尾部的分区数据就是最近最久未被访问的分区数据，替换时直接将链表尾部的分区数据删除。

## spark on k8s和on yarn对比有什么优势？
    Yarn是资源管理工具，也就是说管理CPU+MEM的资源隔离。
    k8s是容器编排工具，显然，资源管理是其功能之一。
    
    spark做为基于jvm的通用计算引擎，跑在yarn和k8s上没啥区别，该有的功能都能有。但是在未来整个数据处理的链路/生态上，
    yarn的能力不如k8s。yarn提供的JAVA进程级别的container天生不适合jvm体系以外的计算框架，比如tensorflow。
    而k8s的容器则完美契合。另外，yarn在企业中，只能用来管理离线的计算资源，k8s则是在线离线通吃，这又是另一个优势了。
    所以，总结下来就是一句话，on k8s可以端到端地完成整个数据流。

