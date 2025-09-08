## Iceberg简介
    

### 数据湖三剑客 Hudi、Delta、Iceberg 对比
    Apache Hudi、Apache Iceberg 和 Delta Lake是目前为数据湖设计的最佳格式。这三种格式都解决了数据湖最迫切的一些问题。
    1.原子事务–保证对数据湖的更新或追加操作不会中途失败，产生脏数据。
    2.一致的更新–防止在写入过程中读取失败或返回不完整的结果。同时处理潜在的并发写入冲突。
    3.数据和元数据的可扩展性–当表增长到数千个分区和数十亿文件的大小时，避免对象存储API和相关元数据的瓶颈。
    
    Hudi
    Hudi最初是由Uber开源的，它被设计成支持通过列式数据格式进行增量更新。它支持从多个来源摄取数据，主要是Apache Spark和Apache Flink。
    它还提供了一个基于Spark的实用程序（DeltaStreamer）来读取外部资源，如Apache Kafka。
    支持从Apache Hive、Apache Impala和PrestoDB读取数据。还有一个专门的工具（HiveSyncTool）可以将Hudi表模式同步到Hive Metastore 中。PS: Hudi社区也支持DLA
    
    iceberg
    Iceberg最初由Netflix发布，旨在解决在S3上存储大型Hive-Partitioned数据集时出现的性能、可扩展性和可管理性挑战。
    iceberg支持Apache Spark的读和写，包括Spark的结构化流。Trino(PrestoSQL)也支持读取，但对删除的支持有限。
    同时支持Apache Flink的读和写。最后，Iceberg为Apache Hive提供了读支持。
    
    Delta Lake
    Delta Lake是由Databricks（Apache Spark的创建者）作为一个开源项目进行维护，并不奇怪，它与Spark在读写方面都进行了深度集成。
    使用Hive的SymlinkTextInputFormat为Presto、AWS Athena、AWS Redshift Spectrum和Snowflake提供读取支持。
    尽管这需要为每个Delta表分区导出一个symlink.txt文件，而且正如你所说，对于较大的表来说，维护成本变得很高。

### 应用场景
    Hudi
    你使用各种查询引擎，并且需要灵活管理变更的数据集。请注意，支持工具和整体的开发者体验可能较差。尽管有可能，但为实际的大规模生产工作负载安装和调试Hudi也需要一定的操作开销。
    如果你正在使用AWS管理服务，如Athena，Glue或EMR - Hudi已经预装和配置，并由AWS支持。
    
    Iceberg
    你的主要痛点不是对现有记录的更改，而是在对象存储上管理巨大的表（超过10k个分区）的元数据负担。采用Iceberg将缓解与S3对象列表或Hive Metastore分区枚举相关的性能问题。
    相反，对删除和变更的支持还是初步的，而且涉及到数据保留的操作开销。
    
    Delta Lake
    你主要是一个Spark商店，并且期望相对较低的写入吞吐量。如果你也已经是Databricks的客户，Delta Engine为读写性能和并发量都带来了显著的改善，对他们的生态系统进行双重开发是很有意义的。
    对于其他Apache Spark发行版来说，要明白Delta Lake虽然是开源的，但很可能会一直落后于Delta Engine，以起到产品差异化的作用。

