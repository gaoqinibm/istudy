## Druid简介
    阿里巴巴也曾创建过一个开源项目叫 Druid （简称阿里 Druid），它是一个数据库连接池项目。阿里 Druid 和 我们要讨论的
    Druid 没有任何关系，它们解决完全不同的问题

### Druid 对比其他OLAP
    Druid vs Elasticsearch

    Druid在导入过程会对原始数据进行Rollup，而ES会保存原始数据
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