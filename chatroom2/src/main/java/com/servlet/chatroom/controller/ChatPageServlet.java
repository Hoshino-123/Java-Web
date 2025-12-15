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
import java.io.PrintWriter;
import java.util.*;

@WebServlet("/chat")
public class ChatPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String username = (String) req.getSession().getAttribute("username");

        if (username == null) {
            resp.sendRedirect("index.jsp");
            return;
        }


        if ("getMessages".equals(action)) {
            List<ChatMessage> messages = ChatService.getMessagesForUser(username);
            int onlineCount = OnlineUserService.getOnlineCount();

            resp.setContentType("application/json;charset=UTF-8");
            PrintWriter out = resp.getWriter();

            out.print("{\"messages\":[");
            for (int i = 0; i < messages.size(); i++) {
                ChatMessage msg = messages.get(i);
                out.print("{");
                out.print("\"username\":\"" + escapeJson(msg.getUsername()) + "\",");
                out.print("\"content\":\"" + escapeJson(msg.getContent()) + "\",");
                out.print("\"timestamp\":\"" + escapeJson(msg.getFormattedTime()) + "\",");
                out.print("\"system\":" + msg.isSystem() + ",");
                out.print("\"targetUser\":");
                if (msg.getTargetUser() == null) {
                    out.print("null");
                } else {
                    out.print("\"" + escapeJson(msg.getTargetUser()) + "\"");
                }
                out.print("}");
                if (i < messages.size() - 1) out.print(",");
            }
            out.print("],\"onlineCount\":" + onlineCount + "}");
            out.flush();
            return;
        }

    
        List<ChatMessage> messages = ChatService.getMessagesForUser(username);
        int onlineCount = OnlineUserService.getOnlineCount();
        Set<String> allOnline = OnlineUserService.getOnlineUsers();
        List<String> otherUsers = new ArrayList<>(allOnline);
        otherUsers.remove(username);

        req.setAttribute("messages", messages);
        req.setAttribute("onlineCount", onlineCount);
        req.setAttribute("username", username);
        req.setAttribute("otherUsers", otherUsers);
        req.getRequestDispatcher("/chat.jsp").forward(req, resp);
    }

    private String escapeJson(String str) {
        if (str == null) return "";
        return str.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r");
    }
}