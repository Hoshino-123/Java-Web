<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>搜索结果</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container">
    <h2>搜索结果</h2>
    <c:choose>
        <c:when test="${empty items}">
            <p>没有找到相关物品。</p>
        </c:when>
        <c:otherwise>
            <c:forEach items="${items}" var="item">
                <div class="item-card">
                    <h3>${item.title}</h3>
                    <p>${item.description}</p>
                    <p>价格：¥${item.price}</p>
                </div>
            </c:forEach>
        </c:otherwise>
    </c:choose>
    <a href="dashboard">返回主页</a>
</div>
</body>
</html>