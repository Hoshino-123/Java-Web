document.getElementById('registerForm').addEventListener('submit', function(e) {
    e.preventDefault();

    const username = document.getElementById('regUsername').value;
    const password = document.getElementById('regPassword').value;

    // 简单验证
    if(username.trim() === '' || password.trim() === '') {
        showError('请输入用户名和密码');
        return;
    }

    // 发送注册请求
    fetch('register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8',
        },
        body: `username=${encodeURIComponent(username)}&password=${encodeURIComponent(password)}`
    })
        .then(response => {
            if(response.redirected) {
                window.location.href = response.url;
            } else {
                return response.text();
            }
        })
        .then(html => {
            if(html) {
                // 如果返回HTML，检查是否有成功或错误信息
                const parser = new DOMParser();
                const doc = parser.parseFromString(html, 'text/html');
                const success = doc.querySelector('.success-message');
                const error = doc.querySelector('.error-message');

                if(success && success.textContent.trim() !== '' && success.textContent.trim() !== '${success}') {
                    showSuccess(success.textContent.trim());
                    setTimeout(() => {
                        window.location.href = 'index.jsp';
                    }, 3000);
                } else if(error && error.textContent.trim() !== '' && error.textContent.trim() !== '${error}') {
                    showError(error.textContent.trim());
                }
            }
        })
        .catch(error => {
            console.error('注册请求失败:', error);
            showError('注册请求失败，请重试');
        });
});

function showSuccess(message) {
    const successDiv = document.getElementById('successMessage');
    successDiv.textContent = message;
    successDiv.style.display = 'block';

    document.getElementById('errorMessage').style.display = 'none';
}

function showError(message) {
    const errorDiv = document.getElementById('errorMessage');
    errorDiv.textContent = message;
    errorDiv.style.display = 'block';

    document.getElementById('successMessage').style.display = 'none';
}

// 显示服务器返回的消息
document.addEventListener('DOMContentLoaded', function() {
    const successMessage = document.querySelector('.success-message');
    const errorMessage = document.querySelector('.error-message');

    if (successMessage.textContent.trim() !== '' && successMessage.textContent.trim() !== '${success}') {
        showSuccess(successMessage.textContent.trim());
        setTimeout(() => {
            window.location.href = 'index.jsp';
        }, 3000);
    }

    if (errorMessage.textContent.trim() !== '' && errorMessage.textContent.trim() !== '${error}') {
        showError(errorMessage.textContent.trim());
    }
});




