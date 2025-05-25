## Hadoop YARN集群搭建指南
### 一.准备工作
1.硬件要求
* 至少3台服务器（1个主节点，2个从节点）
* 每台服务器建议配置：
  + CPU: 4核以上
  + 内存: 8GB以上
  + 硬盘: 50GB以上
  
2.软件要求
* Linux操作系统（推荐CentOS 7或Ubuntu 18.04+）
* Java JDK 8或11（推荐OpenJDK）
* Hadoop 3.x版本

### 二.环境配置
1. 设置主机名和hosts文件
```` 
# 设置主机名（在各节点分别设置）
hostnamectl set-hostname master  # 主节点
hostnamectl set-hostname slave1 # 从节点1
hostnamectl set-hostname slave2 # 从节点2

# 编辑/etc/hosts文件，添加所有节点的IP和主机名映射
vi /etc/hosts

192.168.1.100 master
192.168.1.101 slave1
192.168.1.102 slave2
````
2. 配置SSH免密登录
````
在主节点上执行：
ssh-keygen -t rsa
ssh-copy-id master 
ssh-copy-id slave1
ssh-copy-id slave2
````
3. 安装Java
````
在所有节点上安装Java(略)
````

### 三、Hadoop安装与配置
1. 下载并解压Hadoop
````
在主节点上执行：
tar -zxvf hadoop-3.4.1.tar.gz
````
2. 配置环境变量
````
在所有节点上编辑~/.bashrc或/etc/profile：
export HADOOP_HOME=/usr/local/hadoop/hadoop-3.4.1
export PATH=$PATH:$HADOOP_HOME/bin:$HADOOP_HOME/sbin
export JAVA_HOME=/usr/local/java/jdk1.8.0_451

使环境变量生效：   
source /etc/profile
````
4. 配置Hadoop核心文件
1) hadoop-env.sh
````
cd $HADOOP_HOME/etc/hadoop
vi hadoop-env.sh

添加/修改
export JAVA_HOME=/usr/local/java/jdk1.8.0_451
````
2) core-site.xml
   ````
   <configuration>
       <property>
           <name>fs.defaultFS</name>
           <value>hdfs://master:9000</value>
       </property>
       <property>
           <name>hadoop.tmp.dir</name>
           <value>/usr/local/hadoop/tmp</value>
       </property>
   </configuration>
   ````
3) hdfs-site.xml
   ````
   <configuration>
       <property>
           <name>dfs.replication</name>
           <value>2</value>
       </property>
       <property>
           <name>dfs.namenode.name.dir</name>
           <value>file:///usr/local/hadoop/hdfs/name</value>
       </property>
       <property>
           <name>dfs.datanode.data.dir</name>
           <value>file:///usr/local/hadoop/hdfs/data</value>
       </property>
   </configuration>
   ````
4) yarn-site.xml
   ````
   <configuration>
       <property>
           <name>yarn.nodemanager.aux-services</name>
           <value>mapreduce_shuffle</value>
       </property>
       <property>
           <name>yarn.resourcemanager.hostname</name>
           <value>master</value>
       </property>
   </configuration>
   ````
5) mapred-site.xml
   ````
   <configuration>
       <property>
           <name>mapreduce.framework.name</name>
           <value>yarn</value>
       </property>
   </configuration>
   ````
6) workers
   ````
   添加从节点主机名：
   slave1
   slave2
   ````

4. 将配置分发到从节点
   ````
   scp -r /usr/local/hadoop/hadoop-3.4.1 slave1:/usr/local/hadoop/
   scp -r /usr/local/hadoop/hadoop-3.4.1 slave2:/usr/local/hadoop/
   scp /etc/profile slave1:/etc/profile
   scp /etc/profile slave2:/etc/profile
   ````

### 四、启动集群
1. 格式化HDFS（仅在第一次启动时执行）
   ````
   在主节点上执行：
   hdfs namenode -format
   ````
2. 启动HDFS
   ````
   start-dfs.sh
   ````
3. 启动YARN
   ````
   start-yarn.sh
   ````
4. 验证集群状态
   ````
   # 检查HDFS
   hdfs dfsadmin -report
   
   # 检查YARN
   yarn node -list
   
   # 访问Web界面
   # HDFS: http://master:9870
   # YARN: http://master:8088
   ````
### 五、停止集群
   ````
   stop-yarn.sh
   stop-dfs.sh
   ````
### 六、常见问题解决
+ 1.端口冲突：检查9870、8088等端口是否被占用
+ 2.权限问题：确保所有节点上的hadoop目录权限一致
+ 3.SSH连接问题：检查/etc/hosts和SSH配置
+ 4.Java路径问题：确认JAVA_HOME设置正确

  通过以上步骤，您应该能够成功搭建一个基本的Hadoop YARN集群。根据实际需求，您可能需要进一步调整配置参数以优化集群性能。
