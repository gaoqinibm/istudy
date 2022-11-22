## Greenplum简介
    关系型分布式数据库,它在开源的PostgreSQL的基础上采用MPP架构,具有强大的大规模数据分析任务处理能力。
    Greenplum大数据平台基于MPP(大规模并行处理)架构，具有强大的内核技术，包括数据水平分布、并行查询执行、
    专业优化器、线性扩展能力、多态存储、资源管理、高可用、高速数据加载等。

### Greenplum架构
    GreenPlum数据库是典型的Master/Slave架构。
    主节点(Master)接收客户端的连接和SQL语句，从节点(Segment)存储数据并执行主节点分配的数据操作。
    主节点中会保存Greenplum数据库系统相关的表。从节点是一个个独立的PostgreSQL数据库实例，其中会保存用户数据，并且执行实际的数据操作。
    Greenplum使用的是UDP协议，同时由Greenplum来保住数据包的有效性，因此传输可靠性与TCP协议相当，性能和可扩展性远超TCP协议。

### greenplum应用场景
    greenplum的主要特点：查询速度快、数据装载速度快、批量DML处理快、性能可以随着硬件的添加呈线性增加、拥有非常良好的可扩展性。
    主要适用于面向分析的应用，如构建企业级ODS/EDW、数据集市等