## Hbase数据模型
    RowKey：每行数据必须拥有一个唯一的行键，类似关系型数据库的主键
    Column Family：HBase的每个列都归属于一个列簇，类似于子表概念
    Column：数据属性字段，即表字段
    Version：每次新增或修改数据都会产生一个新版本
    总结：HBase的数据模型由行键、列簇，列名和版本号组成
    
## Region和RegionServers
    Region被分配到的集群节点称为RegionServers，RegionServers负责提供HBase中数据的读写功能。一个RegionServers
    可以容纳大约1000个Region，每个Region包含一部分数据。
    
## HBase的存储
    为了提高数据写入时的吞吐量，HBase并不会实时的讲写入数据直接刷入磁盘，而是先将数据放入内存中进行保管。
    将数据直接放入内存读写虽然很快，但这样不安全，一旦服务器重启数据全部丢失。HBase采用预写日志结合MemStroe来解决。
    当客户端向HBase发起写入请求的时候，HBase首先会通过RegionServers将数据写入预写日志，之后在用MemStroe对象将数据
    保存到内存中。由于有了预写日志，当服务出现故障重启后，Region可以通过日志将数据复原到MemStroe。然后当一个MemStroe
    存储的数据达到某一个阈值时，HBase会将这个MemStroe的数据通过HFile的形式写入到磁盘并清空该MemStroe。

## 使用场景
#### 存储/查询
* 适合大数据的实时写入场景和毫秒级的查询
* 适合在流计算、用户行为数据存储等场景