## Clickhouse使用技巧
### mysql与clickhouse的字段类型对应表
![Alt text](../doc/字段类型对比.png)

### clickhouse 数据类型
数据类型没有boolean其他基本和hive一样,详细的看官网 https://clickhouse.tech/docs/en/sql-reference/data-types/int-uint/

### clickhouse 引擎
    clickhouse有很多引擎,最常用的是 MergeTree家族 还有Distributed引擎

### clickhouse 创建表
> clickhouse可以创建本地表,分布式表,集群表

    create table test()为本地表
    CREATE TABLE image_label_all AS image_label ENGINE = Distributed(distable, monchickey, image_label, rand()) 分布式表
    create table test on cluster()为集群表
    
    建表语句,使用ReplicatedMergeTree引擎
    CREATE TABLE metro.metro_mdw_pcg (
     storekey Int32, 
     custkey Int32,  
    cardholderkey Int32,  
    pcg_main_cat_id Int32,  
    pcg_main_cat_desc String,  
    count Int32,  
    quartly String
    ) ENGINE = ReplicatedMergeTree('/clickhouse/tables/metro/metro_mdw_pcg', '{replica}') PARTITION BY (quartly, pcg_main_cat_id) 
    ORDER BY (storekey, custkey, cardholderkey)
    
### clickhouse数据操作
    增加可以使用insert;
    不能修改,也不能指定删除;
    可以删除分区,会删除对应的数据 我使用--help看了一下有truncate table,但是没有具体使用过,如果要全部删除数据可以删除表,然后在建表查数据
    可以使用脚本操作

