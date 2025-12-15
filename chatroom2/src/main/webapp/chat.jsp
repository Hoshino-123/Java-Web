<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>在线聊天室</title>
    <link rel="stylesheet" href="css/chat.css">
</head>
<body>
<div class="header">
    <div class="user-info">
        欢迎, <span id="currentUsername">${username}</span> |
        在线人数: ${onlineCount}
    </div>
    <button class="logout-btn" onclick="location.href='logout'">退出登录</button>
</div>

<div class="chat-container">
    <div class="messages" id="messagesContainer">
        <c:forEach items="${messages}" var="msg">
            <c:set var="isOwn" value="${msg.sender == username}" />
            <div class="message ${isOwn ? 'own-message' : 'other-message'}">
                <div class="message-header">
                    <c:choose>
                        <c:when test="${msg.system}">系统消息</c:when>
                        <c:otherwise>
                            ${msg.sender}
                            <c:if test="${msg.targetUser != null}">
                                ➝ ${msg.targetUser}
                            </c:if>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="message-content">${msg.content}</div>
                <div class="timestamp">${msg.formattedTime}</div>
            </div>
        </c:forEach>
    </div>

    <div class="input-area">
        <form action="sendMessage" method="post" onsubmit="return handleSend()">
            <select name="targetUser" id="targetUserSelect">
                <option value="all">所有人</option>
                <c:forEach items="${otherUsers}" var="user">
                    <option value="${user}">私聊: ${user}</option>
                </c:forEach>
            </select>
            <input type="text" name="messageInput" id="messageInput"
                   placeholder="输入消息..." required autocomplete="off">
            <button type="submit" class="send-btn">发送</button>
        </form>
    </div>
</div>
<script src="js/chat.js"></script>
</body>
</html>