package com.servlet.chatroom.controller;

import com.servlet.chatroom.entity.ChatMessage;
import com.servlet.chatroom.service.ChatService;
import com.servlet.chatroom.service.OnlineUserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sendMessage")
public class ChatServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = (String) req.getSession().getAttribute("username");
        if (username == null) {
            resp.sendRedirect("index.jsp");
            return;
        }

        String content = req.getParameter("messageInput");
        String targetParam = req.getParameter("targetUser");

        if (content == null || content.trim().isEmpty()) {
            resp.sendRedirect("chat");
            return;
        }

        ChatMessage msg = new ChatMessage(username, content.trim());

        if ("all".equals(targetParam)) {
            msg.setTargetUser(null);
        } else {
            String targetUser = targetParam;
            if (!OnlineUserService.isUserOnline(targetUser)) {
                resp.sendRedirect("chat?error=对方不在线，无法私聊");
                return;
            }
            msg.setTargetUser(targetUser);
        }

        ChatService.addMessage(msg);
        resp.sendRedirect("chat");
    }
}