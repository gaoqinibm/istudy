## Druid简介
    阿里巴巴也曾创建过一个开源项目叫Druid（简称阿里Druid），它是一个数据库连接池项目。阿里Druid和我们要讨论的
    Druid没有任何关系，它们解决完全不同的问题
    Apache Druid是一个分布式内存实时分析系统，用于解决如何在大规模数据集下快速的、交互式的查询和分析问题。

### Druid对比其他OLAP
    Druid vs Elasticsearch

    Druid在导入过程会对原始数据进行Rollup(汇总)，而ES会保存原始数据
    Druid专注于OLAP，针对数据导入以及快速聚合操作做了优化
    Druid不支持全文检索
>

    Druid vs Key/Value Stores (HBase/Cassandra/OpenTSDB)
    
    Druid采用列式存储，使用倒排和bitmap索引，可以做到快速扫描相应的列
>

    Druid vs Spark
    
    Spark SQL的响应还不做到亚秒
    Druid可以做到超低的响应时间，例如亚秒，而且高并发面向用户的应用。
>

    Druid vs SQL-on-Hadoop (Impala/Drill/Spark SQL/Presto)
    
    Driud查询速度更快
    数据导入，Druid支持实时导入，SQL-on-Hadoop一般将数据存储在Hdfs上，Hdfs的写入速度有可能成为瓶颈
    SQL支持，Druid也支持SQL，但Druid不支持Join操作
>

    Druid vs Kylin
    
    Kylin不支持实时查询，Druid支持
    Kylin支持表连接（Join），Druid不支持

### Apache Druid具有如下特点
    支持亚秒级的交互式查询。例如，多维过滤、Ad-hoc的属性分组和快速聚合数据。
    支持实时的数据消费。
    支持多租户同时在线查询。
    支持PB级数据、千亿级事件快速处理，支持每秒数千查询并发。
    支持高可用，并且滚动升级。

### 应用场景
    实时数据分析是Apache Druid最典型的使用场景。该场景涵盖的面很广，例如：
    实时指标监控
    推荐模型
    广告平台
    搜索模型

### Apache Druid架构
