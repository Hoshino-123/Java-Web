# 🛍️ 二手物品交易平台（Secondhand Marketplace）

一个基于 **Java Servlet + JSP + MySQL** 的轻量级二手物品交易网站，支持用户注册登录、发布物品、浏览全站商品、管理个人物品及删除功能。

---

## 🔧 功能特性

- ✅ 用户注册与登录（密码加密存储）
- ✅ 发布二手物品（标题、描述、价格）
- ✅ 浏览全站所有物品（按发布时间倒序）
- ✅ 查看自己发布的物品列表
- ✅ 删除自己的物品（带确认提示）
- ✅ 简易关键词搜索（按标题模糊匹配）
- ✅ 响应式基础页面（适配 PC 与手机）

---

## 🛠 技术栈
- **架构**：MVC 架构  
- **后端**：Java 17+、Servlet 5.0（Jakarta EE 11）、JSP  
- **前端**：HTML5、CSS3、JavaScript  
- **数据库**：MySQL 8.0+  
- **构建工具**：Maven  
- **服务器**：Apache Tomcat 10  
---

## 🚀 快速启动

### 1. 创建数据库

```sql
CREATE DATABASE IF NOT EXISTS secondhand CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE secondhand;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL
);

CREATE TABLE items (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    user_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
```
### 2. 配置数据库连接
- 修改 src/main/java/com/servlet/secondhand/dao;
``` private static final String URL = "jdbc:mysql://localhost:3306/secondhand?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "username"; //  改为你自己的数据库用户名
    private static final String PASSWORD = "password"; // 改为你自己的密码
```

### 3. 构建并部署
- Maven 项目：运行 mvn clean package，将生成的 .war 文件放入 Tomcat 的 webapps/ 目录
- IDE 直接运行：在 Eclipse/IDEA 中配置 Tomcat Server，直接启动

### 4. 访问应用
- 首页：http://localhost:8080/secondhand


---


