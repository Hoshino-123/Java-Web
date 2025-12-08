<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>注册</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="form-container">
    <h2>用户注册</h2>
    <c:if test="${param.error != null}">
        <p style="color:red;">用户名已存在！</p>
    </c:if>
    <form action="register" method="post">
        <input type="text" name="username" placeholder="用户名" required><br>
        <input type="password" name="password" placeholder="密码" required><br>
        <button type="submit">注册</button>
    </form>
    <p>已有账号？<a href="index.jsp">去登录</a></p>
</div>
</body>
</html>
