<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>我的物品</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container">
    <h1>我的二手物品</h1>

    <a href="dashboard">返回用户中心</a> |
    <a href="post.jsp">发布新物品</a>

    <c:choose>
        <c:when test="${empty myItems}">
            <p>你还没有发布任何物品。</p>
        </c:when>
        <c:otherwise>
            <table border="1" style="width:100%; margin-top:20px; border-collapse: collapse;">
                <thead>
                <tr>
                    <th>标题</th>
                    <th>描述</th>
                    <th>价格</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${myItems}" var="item">
                    <tr>
                        <td>${item.title}</td>
                        <td>${item.description}</td>
                        <td>¥${item.price}</td>
                        <td>
                            <form action="delete-item" method="post" style="display:inline;">
                                <input type="hidden" name="itemId" value="${item.id}" />
                                <button type="submit" onclick="return confirm('确定删除？')">删除</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>