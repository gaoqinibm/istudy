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

### 示例
    database=$1
    table=$2
    echo "Truncate table "$2
    create=`clickhouse-client --database=$1 --query="SHOW CREATE TABLE $table" | tr -d '\\'`
    clickhouse-client --database=$1 --query="DROP TABLE $table"
    clickhouse-client --database=$1 --query="$create"
    
    再导入数据就可以了
    导入数据,clickhouse支持很多文件类型 详见 https://clickhouse.tech/docs/en/interfaces/formats/
    经常用的文件的导入
    tsv,以"\t"隔开
    clickhouse-client -h badd52c42f08 --input_format_allow_errors_num=1 --input_format_allow_errors_ratio=0.1 --query="INSERT INTO tablename FORMAT TSV"<file
    csv 以","或者"|"隔开
    clickhouse-client -h adc3eaba589c --format_csv_delimiter="|" --query='INSERT INTO tablename FORMAT CSV' < file
    
### 数据查询
    clickhouse的 查询sql 表单查询基本和标准sql一样,也支持limit 分页,但是inner join 的查询写法不一样,而且我用4亿+2000万inner join的速度很慢
    两个sql对比 inner join要花费将近一分钟,使用in子查询仅3秒, 建议都使用in查询,clickhouse的单表查询速度很快,3亿数据count distinct 仅1秒左右
    
### clickhouseSQL语法
> CREATE

    #方式1
    CREATE TABLE [IF NOT EXISTS] [db.]table_name [ON CLUSTER cluster]
    (
        name1 [type1] [DEFAULT|MATERIALIZED|ALIAS expr1],
        name2 [type2] [DEFAULT|MATERIALIZED|ALIAS expr2],
        ...
    ) ENGINE = engine
    #方式2
    CREATE TABLE [IF NOT EXISTS] [db.]table_name AS [db2.]name2 [ENGINE = engine]
    #方式3
    CREATE TABLE [IF NOT EXISTS] [db.]table_name ENGINE = engine AS SELECT ...
    
> INSERT INTO

    #方式1-交互式
    INSERT INTO [db.]table [(c1, c2, c3)] VALUES (v11, v12, v13), ...
    
    INSERT INTO [db.]table [(c1, c2, c3)] SELECT ...
    
    #方式2-批量
    cat file.csv | clickhouse-client --database=test --query="INSERT INTO test FORMAT CSV"
    
    #方式3-http客户端
    echo -ne '10\n11\n12\n' | POST 'http://localhost:8123/?query=INSERT INTO t FORMAT TabSeparated'
    
> SELECT

    SELECT [DISTINCT] expr_list
        [FROM [db.]table | (subquery) | table_function] [FINAL]
        [SAMPLE sample_coeff]
        [ARRAY JOIN ...]
        [GLOBAL] ANY|ALL INNER|LEFT JOIN (subquery)|table USING columns_list
        [PREWHERE expr]
        [WHERE expr]
        [GROUP BY expr_list] [WITH TOTALS]
        [HAVING expr]
        [ORDER BY expr_list]
        [LIMIT [n, ]m]
        [UNION ALL ...]
        [INTO OUTFILE filename]
        [FORMAT format]
        [LIMIT n BY columns]

> ALTER

    ALTER查询仅支持* MergeTree族表引擎，以及Merge表引擎和Distributed表引擎。
    ALTER操作阻塞所有对表的其他操作。
    
    #添加列
    ALTER TABLE [db].name [ON CLUSTER cluster] ADD COLUMN [IF NOT EXISTS] name [type] [default_expr] [AFTER name_after]
    #删除列
    ALTER TABLE [db].name [ON CLUSTER cluster] DROP COLUMN [IF EXISTS] name
    #重置指定分区中列的所有数据
    ALTER TABLE [db].name [ON CLUSTER cluster] CLEAR COLUMN [IF EXISTS] name IN PARTITION partition_name
    #添加列注解
    ALTER TABLE [db].name [ON CLUSTER cluster] COMMENT COLUMN [IF EXISTS] name 'comment'
    #修改列类型或者列的默认值
    ALTER TABLE [db].name [ON CLUSTER cluster] MODIFY COLUMN [IF EXISTS] name [type] [default_expr]
    #添加索引
    ALTER TABLE [db].name ADD INDEX name expression TYPE type GRANULARITY value AFTER name [AFTER name2]
    #删除索引
    ALTER TABLE [db].name DROP INDEX name
    #分离分区
    ALTER TABLE table_name DETACH PARTITION partition_expr
    #删除分区
    ALTER TABLE table_name DROP PARTITION partition_expr
    #添加被分离的分区
    ALTER TABLE table_name ATTACH PARTITION|PART partition_expr
    #复制table1中的分区数据到table2
    ALTER TABLE table2 REPLACE PARTITION partition_expr FROM table1
    #重置列值为默认值，默认值为创建表时指定
    ALTER TABLE table_name CLEAR COLUMN column_name IN PARTITION partition_expr
    #创建指定分区或者所有分区的备份
    ALTER TABLE table_name FREEZE [PARTITION partition_expr]
    #从其他分片中复制分区数据
    ALTER TABLE table_name FETCH PARTITION partition_expr FROM 'path-in-zookeeper'

> clickhouse导入数据

    方式一：交互式
    insert into tableName (c1, c2, ...) values (v1, v2, ...)
    insert into tableName (c1, c2, ...) select ... 
    方式二：批量
    clickhouse-client --quey="insert into tableName format CSV" < file.csv
    方式三：http客户端
    echo -ne '10\n11\n12\n' | POST 'http://localhost:8123/?query=insert into tableName format TabSeparated'

> clickhouse导出数据

    方式一：交互式
    select * from tableName into outfile 'path/file'
    方式二：非交互式
    clickhouse-client  --database bdName -u default --password password --query='select * from tableName' > abc
    方式二：http客户端
    echo 'select 1 FORMAT TabSeparated' | curl "http://user:password@localhost:8123/" -d @- > file