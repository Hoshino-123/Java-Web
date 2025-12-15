package com.servlet.chatroom.util;

import com.servlet.chatroom.service.ChatService;
import com.servlet.chatroom.service.OnlineUserService;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        String username = (String) se.getSession().getAttribute("username");
        if (username != null) {
            OnlineUserService.removeUser(username);
            ChatService.addSystemMessage(username + " 下线了");
        }
    }
}



