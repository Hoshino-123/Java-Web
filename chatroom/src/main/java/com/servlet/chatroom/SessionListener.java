// src/main/java/com/servlet/chatroom/SessionListener.java
package com.servlet.chatroom;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        String username = (String) se.getSession().getAttribute("username");
        if (username != null) {
            OnlineUserManager.removeUser(username);
        }
    }
}