<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>发布物品</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="form-container">
    <h2>发布二手物品</h2>
    <form action="post-item" method="post">
        <input type="text" name="title" placeholder="物品标题" required><br>
        <textarea name="description" placeholder="物品描述" rows="4"></textarea><br>
        <input type="number" step="0.01" name="price" placeholder="价格（元）" min="0" required><br>
        <button type="submit">发布</button>
    </form>
    <a href="dashboard">返回主页</a>
</div>
</body>
</html>