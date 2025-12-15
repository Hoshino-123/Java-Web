// 获取当前用户
const currentUser = document.getElementById('currentUsername').textContent || '游客';

// 加载聊天消息
function loadMessages() {
    fetch('chat?action=getMessages')
        .then(response => response.json())
        .then(messages => {
            const container = document.getElementById('messagesContainer');
            container.innerHTML = ''; // 清空现有消息

            messages.forEach(message => {
                displayMessage(message);
            });

            scrollToBottom();
        })
        .catch(error => {
            console.error('加载消息失败:', error);
        });
}

function sendMessage() {
    const input = document.getElementById('messageInput');
    const content = input.value.trim();

    if(content === '') return;

    // 发送消息到服务器
    fetch('chat', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8',
        },
        body: `content=${encodeURIComponent(content)}`
    })
        .then(response => {
            if(response.ok) {
                input.value = '';
                // 重新加载消息以显示最新消息
                loadMessages();
            }
        })
        .catch(error => {
            console.error('发送消息失败:', error);
        });
}

function displayMessage(message) {
    const container = document.getElementById('messagesContainer');
    const messageDiv = document.createElement('div');
    messageDiv.className = message.username === currentUser ? 'message own-message' : 'message other-message';

    messageDiv.innerHTML = `
        <div class="message-header">${message.username}</div>
        <div class="message-content">${message.content}</div>
        <div class="timestamp">${message.timestamp}</div>
    `;

    container.appendChild(messageDiv);
}

function handleKeyPress(event) {
    if(event.key === 'Enter') {
        sendMessage();
    }
}

function scrollToBottom() {
    const container = document.getElementById('messagesContainer');
    container.scrollTop = container.scrollHeight;
}

function logout() {
    window.location.href = 'logout';
}

// 页面加载时获取消息
document.addEventListener('DOMContentLoaded', function() {
    loadMessages();

    // 每5秒自动刷新消息
    setInterval(loadMessages, 5000);
});


