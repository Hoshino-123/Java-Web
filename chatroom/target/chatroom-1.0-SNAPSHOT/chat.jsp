<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>在线聊天室</title>
    <link rel="stylesheet" href="css/chat.css">
</head>
<body>
<div class="header">
    <div class="user-info">    欢迎, <span id="currentUsername">${username}</span> |
        在线人数: ${onlineCount}
    </div>
    <button class="logout-btn" onclick="logout()">退出登录</button>
</div>

<div class="chat-container">
    <div class="messages" id="messagesContainer">
    </div>

    <div class="input-container">
        <input type="text" class="message-input" id="messageInput" placeholder="输入消息..." >
        <button class="send-btn" onclick="sendMessage()">发送</button>
    </div>
</div>

<script src="js/chat.js"></script>
</body>
</html>