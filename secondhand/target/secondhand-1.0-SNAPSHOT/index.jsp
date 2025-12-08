<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="form-container">
    <h2>用户登录</h2>
    <c:if test="${param.error != null}">
        <p style="color:red;">用户名或密码错误！</p>
    </c:if>
    <form action="login" method="post">
        <input type="text" name="username" placeholder="用户名" required><br>
        <input type="password" name="password" placeholder="密码" required><br>
        <button type="submit">登录</button>
    </form>
    <p>还没有账号？<a href="register.jsp">立即注册</a></p>
</div>
</body>
</html>