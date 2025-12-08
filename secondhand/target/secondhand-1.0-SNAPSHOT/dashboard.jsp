<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户中心</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container">
    <h1>欢迎，${user.username}！</h1>
    <a href="post.jsp">发布二手物品</a> |
    <a href="myitems">我的物品</a> |
    <a href="logout">退出登录</a>
    <c:if test="${param.posted != null}">
        <p style="color:green;">物品发布成功！</p>
    </c:if>

    <form action="search" method="get" style="margin: 20px 0;">
        <input type="text" name="q" placeholder="搜索物品标题..." required>
        <button type="submit">搜索</button>
    </form>

    <h2>所有二手物品</h2>
    <c:choose>
        <c:when test="${empty items}">
            <p>暂无物品发布。</p>
        </c:when>
        <c:otherwise>
            <c:forEach items="${items}" var="item">
                <div class="item-card">
                    <h3>${item.title}</h3>
                    <p>${item.description}</p>
                    <p><strong>¥${item.price}</strong></p>
                </div>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
