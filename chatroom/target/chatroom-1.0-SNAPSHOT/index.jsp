<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>在线聊天室 - 登录</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container">
    <h2 class="form-title">在线聊天室 - 登录</h2>
    <form id="loginForm" action="login" method="post">
        <div class="form-group">
            <label for="username">用户名:</label>
            <input type="text" id="username" name="username" required>
        </div>
        <div class="form-group">
            <label for="password">密码:</label>
            <input type="password" id="password" name="password" required>
        </div>
        <button type="submit" class="btn">登录</button>
    <div class="link">
        <a href="register.jsp">还没有账号？点击注册</a>
    </div>
    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>
</div>
</form>

</body>
</html>