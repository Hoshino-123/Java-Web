package com.servlet.secondhand.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dao {
    private static final String URL = "jdbc:mysql://localhost:3306/secondhand?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "username"; //  改为你自己的数据库用户名
    private static final String PASSWORD = "password"; // 改为你自己的密码


    static {
        System.out.println("🔧 加载 MySQL 驱动...");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("✅ 驱动类加载成功");
        } catch (Exception e) {
            System.err.println("❌ 驱动类加载失败");
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}