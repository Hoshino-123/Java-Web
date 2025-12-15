<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>在线聊天室 - 注册</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container">
    <h2 class="form-title">在线聊天室 - 注册</h2>
    <form id="registerForm" action="register" method="post">
        <div class="form-group">
            <label for="regUsername">用户名:</label>
            <input type="text" id="regUsername" name="username" required>
        </div>
        <div class="form-group">
            <label for="regPassword">密码:</label>
            <input type="password" id="regPassword" name="password" required>
        </div>
        <button type="submit" class="btn">注册</button>
    </form>
    <div class="link">
        <a href="index.jsp">已有账号？点击登录</a>
    </div>
    <div id="successMessage" class="success-message">
        ${success}
    </div>
    <div id="errorMessage" class="error-message">
        ${error}
    </div>
</div>

<script src="js/register.js"></script>
</body>
</html>