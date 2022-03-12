package com.istudy.datax;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * @Description: TODO
 * @Author Baizhen
 * @Date 2020/4/9 11:15
 */
public class Test {

    private static Admin admin = null;
    private static Connection connection = null;
    private static Configuration conf = null;

    static {
        // HBase配置文件
        conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "hbaseserver");
        conf.set("hbase.zookeeper.property.clientPort", "2181");

        // 获取连接对象
        try {
            connection = ConnectionFactory.createConnection(conf);
            // 获取HBase管理员对象
            admin = connection.getAdmin();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void close(Connection conn, Admin admin) throws IOException {
        if (conn != null) {
            conn.close();
        }
        if (admin != null) {
            admin.close();
        }
    }

    // 判断表是否存在
    public static boolean tableExist(String tableName) throws IOException {
        boolean tableExists = admin.tableExists(TableName.valueOf(tableName));
        return tableExists;
    }

    // 创建表
    public static void createTable(String tableName, String... cfs) throws IOException {
        if (tableExist(tableName)) {
            System.out.println("表已存在");
            return;
        }
        // cfs是列族，官方建议一个表一个，但可以有多个
        // 创建表描述器
        HTableDescriptor hTableDescriptor = new HTableDescriptor(TableName.valueOf(tableName));
        for (String cf : cfs) {
            // 创建列描述器
            HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(cf);
//          hColumnDescriptor.setMaxVersions(3);//设置版本数
            hTableDescriptor.addFamily(hColumnDescriptor);
        }
        // 创建表操作
        admin.createTable(hTableDescriptor);
    }

    // 删除表
    public static void deleteTable(String tableName) throws IOException {
        if (!tableExist(tableName)) {
            System.out.println("表不存在");
            return;
        }
        // 使表不可用（下线）
        admin.disableTable(TableName.valueOf(tableName));
        // 执行删除操作
        admin.deleteTable(TableName.valueOf(tableName));
        System.out.println("表已删除");
    }

    // 增、改
    public static void putData(String tableName, String rowKey, String cf, String cn, String value) throws IOException {
        // 获取表对象
//      HTable table=new HTable(conf,TableName.valueOf(tableName)); 已过时
        Table table = connection.getTable(TableName.valueOf(tableName));
        // 创建put对象
        Put put = new Put(Bytes.toBytes(rowKey));
        // 添加数据
        put.addColumn(Bytes.toBytes(cf), Bytes.toBytes(cn), Bytes.toBytes(value));
        // 执行添加操作
        table.put(put);
    }

    // 删
    public static void delete(String tableName, String rowKey, String cf, String cn) throws IOException {
        // 获取table对象
        Table table = connection.getTable(TableName.valueOf(tableName));
        // 创建delete对象
        Delete delete = new Delete(Bytes.toBytes(rowKey));// 删除整个列族
        delete.addColumns(Bytes.toBytes(cf), Bytes.toBytes(cn));// 删除所有版本
//      delete.addColumn(Bytes.toBytes(cf), Bytes.toBytes(cn));不推荐，只删除最新的版本
        // 执行删除操作
        table.delete(delete);
        table.close();
    }

    // 查——全表扫描，只能获取最新版本
    public static void scanTable(String tableName) throws IOException {
        // 获取table对象
        Table table = connection.getTable(TableName.valueOf(tableName));
        // 构建扫描器
        Scan scan = new Scan();
        ResultScanner resultScanner = table.getScanner(scan);

        // 遍历数据并打印
        for (Result result : resultScanner) { // rowkey
            Cell[] cells = result.rawCells();
            for (Cell cell : cells) { // cell
                System.out.println("RK:" + Bytes.toString(CellUtil.cloneRow(cell)) + ",CF:"
                        + Bytes.toString(CellUtil.cloneFamily(cell)) + ",CN:"
                        + Bytes.toString(CellUtil.cloneQualifier(cell)) + ",VALUE:"
                        + Bytes.toString(CellUtil.cloneValue(cell)));
            }
        }
        table.close();
    }

    // 查——获取指定列族
    public static void getData(String tableName, String rowKey, String cf, String cn) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Get get = new Get(Bytes.toBytes(rowKey));
        get.addColumn(Bytes.toBytes(cf), Bytes.toBytes(cn));
//      get.addFamily(cf);
//      get.setMaxVersions();//不传参默认是表结构内的maxversions
        get.setMaxVersions(2);
        Result result = table.get(get);
        Cell[] cells = result.rawCells();
        for (Cell cell : cells) { // cell
            System.out.println("RK:" + Bytes.toString(CellUtil.cloneRow(cell)) + ",CF:"
                    + Bytes.toString(CellUtil.cloneFamily(cell)) + ",CN:"
                    + Bytes.toString(CellUtil.cloneQualifier(cell)) + ",VALUE:"
                    + Bytes.toString(CellUtil.cloneValue(cell)));
        }
    }

    public static void main(String[] args) throws IOException {
        // 判断表是否存在
//      System.out.println(tableExist("student"));
//      System.out.println(tableExist("staff"));

        // 创建表
      createTable("staff", "info");
      System.out.println(tableExist("staff"));

        // 删除表
//      deleteTable("staff");

        // 增、改
//      putData("student", "1001", "info", "name", "mcq");

        // 删
//      delete("student", "1001", "info", "name");

        // 查——全表扫描
//      scanTable("student");

        //查——获取指定列族
        //getData("student", "1001", "info", "name");

        // 关闭资源
        close(connection, admin);
    }
}
