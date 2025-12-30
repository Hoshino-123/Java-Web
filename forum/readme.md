# 💬 在线问答留言交互平台（Online Forum）  
一个基于 Spring Boot + Thymeleaf 的轻量级在线论坛系统，支持用户注册登录（含图形验证码）、发布帖子、回复讨论。所有数据存储在内存中，适合学习 Spring Boot Web 开发。

## 🔧 功能特性
✅ 用户注册与登录（密码明文存储，仅用于演示）  
✅ 图形验证码（4位字母数字，点击刷新）  
✅ 发布新留言（标题 + 内容）  
✅ 浏览全站留言列表（按发布时间倒序）  
✅ 查看帖子详情及所有回复  
✅ 回复他人帖子  
✅ 响应式基础页面（适配 PC 与手机）

## 🛠 技术栈
**架构**：Spring MVC + Thymeleaf 模板引擎  
**后端**：Java 17+、Spring Boot 3.5.9、Jakarta Servlet  
**前端**：HTML5、CSS3（无 JavaScript）  
**构建工具**：Maven  
**服务器**：内嵌 Tomcat

## 🚀 快速启动

### 1. 克隆项目
```bash
git clone https://github.com/your-username/online-forum.git
cd online-forum
```
### 2. 构建并运行
```bash
mvn clean package -DskipTests
java -jar target/*.jar
```
### 3. 访问应用
- 默认端口：8081（已在 application.properties 中配置）
- 首页：http://localhost:8081
（首次访问将自动跳转到登录页） 
- 修改端口：编辑 src/main/resources/application.properties
```properties
server.port=8081

```
