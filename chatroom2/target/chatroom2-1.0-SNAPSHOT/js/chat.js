const currentUser = document.getElementById('currentUsername')?.textContent.trim() || '游客';
let hasInitialized = false;
let isAtBottom = true;

function displayMessage(msg) {
    const div = document.createElement('div');
    div.className = msg.username === currentUser ? 'message own-message' : 'message other-message';

    let headerText = msg.system ? '系统消息' : msg.username;
    if (!msg.system && msg.targetUser) {
        headerText += ' ➝ ' + msg.targetUser;
    }

    const content = String(msg.content).replace(/</g, '&lt;').replace(/>/g, '&gt;');
    const timestamp = msg.timestamp || new Date().toLocaleTimeString();

    div.innerHTML = `
        <div class="message-header">${headerText}</div>
        <div class="message-content">${content}</div>
        <div class="timestamp">${timestamp}</div>
    `;
    return div;
}

function shouldAutoScroll(container) {
    const scrollTop = container.scrollTop;
    const scrollHeight = container.scrollHeight;
    const clientHeight = container.clientHeight;
    return scrollHeight - scrollTop - clientHeight < 50;
}

function pollMessages() {
    fetch('chat?action=getMessages')
        .then(res => {
            if (!res.ok) throw new Error('Network error');
            return res.json();
        })
        .then(data => {
            const container = document.getElementById('messagesContainer');
            if (!container) return;

            const wasAtBottom = isAtBottom || !hasInitialized;

            container.innerHTML = '';
            data.messages.forEach(msg => {
                container.appendChild(displayMessage(msg));
            });

            const infoDiv = document.querySelector('.user-info');
            if (infoDiv) {
                const namePart = infoDiv.innerHTML.split('|')[0];
                infoDiv.innerHTML = `${namePart} | 在线人数: ${data.onlineCount}`;
            }

            if (wasAtBottom) {
                container.scrollTop = container.scrollHeight;
                isAtBottom = true;
            }

            hasInitialized = true;
        })
        .catch(err => {
            console.error('Polling error:', err);
        });
}

document.addEventListener('DOMContentLoaded', () => {
    const container = document.getElementById('messagesContainer');
    if (container) {
        container.addEventListener('scroll', () => {
            const scrollTop = container.scrollTop;
            const scrollHeight = container.scrollHeight;
            const clientHeight = container.clientHeight;
            isAtBottom = (scrollHeight - scrollTop - clientHeight) < 50;
        });
    }
});

pollMessages();
setInterval(pollMessages, 2000);

