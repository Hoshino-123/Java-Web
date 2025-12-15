package com.servlet.chatroom;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/chat")
public class ChatServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置字符编码
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        String action = request.getParameter("action");
        if ("getMessages".equals(action)) {
            // 返回聊天消息
            PrintWriter out = response.getWriter();
            List<ChatStorage.ChatMessage> messages = ChatStorage.getMessages();

            out.print("[");
            for (int i = 0; i < messages.size(); i++) {
                ChatStorage.ChatMessage msg = messages.get(i);
                out.print("{");
                out.print("\"username\":\"" + escapeJson(msg.getUsername()) + "\",");
                out.print("\"content\":\"" + escapeJson(msg.getContent()) + "\",");
                out.print("\"timestamp\":\"" + msg.getFormattedTime() + "\"");
                out.print("}");
                if (i < messages.size() - 1) {
                    out.print(",");
                }
            }
            out.print("]");
        } else {
            // 默认跳转到聊天界面
            request.getRequestDispatcher("chat.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置字符编码
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain; charset=UTF-8");

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        String username = (String) session.getAttribute("username");
        String content = request.getParameter("content");

        if (content != null && !content.trim().isEmpty()) {
            ChatStorage.addMessage(username, content.trim());
        }

        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write("Message sent successfully");
    }

    private String escapeJson(String input) {
        if (input == null) return null;
        return input.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t")
                .replace("\b", "\\b")
                .replace("\f", "\\f");
    }
}