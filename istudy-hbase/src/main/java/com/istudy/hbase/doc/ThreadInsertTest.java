package com.istudy.hbase.doc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Description: TODO
 * @Author Baizhen
 * @Date 2020/4/27 9:43
 */
public class ThreadInsertTest extends Thread {

    @Override
    public void run() {
        String url = "jdbc:mysql://127.0.0.1:3306/ps_test?useSSL=false&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true";
        String name = "com.mysql.jdbc.Driver";
        String user = "root";
        String password = "123456";
        Connection conn = null;
        try {
            Class.forName(name);
            conn = DriverManager.getConnection(url, user, password);// 获取连接
            System.out.println("数据库连接成功");
            conn.setAutoCommit(false);// 关闭自动提交，不然conn.commit()运行到这句会报错
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 开始时间
        Long begin = System.currentTimeMillis();
        // sql前缀
        String prefix = "INSERT INTO test_data (name, password, sex, description, url, schoolname, remark) VALUES ";
        try {
            // 保存sql后缀
            StringBuffer suffix = new StringBuffer();
            // 设置事务为非自动提交
            conn.setAutoCommit(false);
            // 比起st，pst会更好些

            PreparedStatement pst = (PreparedStatement) conn
                    .prepareStatement("");// 准备执行语句
            // 外层循环，总提交事务次数
            for (int i = 1; i <= 10; i++) {
                suffix = new StringBuffer();
                // 第j次提交步长
                for (int j = 1; j <= 50000; j++) {
                    // 构建SQL后缀
                    suffix.append("('" + i * j + "','123456'" + ",'男'"
                            + ",'教师'" + ",'www.bbb.com'" + ",'Java大学'"
                            + ",'备注'" + "),");
                }
                // 构建完整SQL
                String sql = prefix + suffix.substring(0, suffix.length() - 1);
                // 添加执行SQL
                pst.addBatch(sql);
                // 执行操作
                pst.executeBatch();
                // 提交事务
                conn.commit();
                // 清空上一次添加的数据
                suffix = new StringBuffer();
            }

            // 头等连接
            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 结束时间
        Long end = System.currentTimeMillis();
        // 耗时
        System.out.println("5千万条数据插入花费时间 : " + (end - begin) / 1000 + " s"
                + "  插入完成");
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            new ThreadInsertTest().start();
        }
    }
}
