package com.servlet.chatroom.controller;

import com.servlet.chatroom.service.UserService;
import com.servlet.chatroom.service.OnlineUserService;
import com.servlet.chatroom.service.ChatService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username == null || password == null) {
            req.setAttribute("error", "请输入用户名和密码");
            forwardToLogin(req, resp);
            return;
        }

        if (userService.authenticate(username, password)) {
            req.getSession().setAttribute("username", username);
            // ✅ 上线
            OnlineUserService.addUser(username);
            ChatService.addSystemMessage(username + " 上线了");
            resp.sendRedirect("chat");
        } else {
            req.setAttribute("error", "用户名或密码错误");
            forwardToLogin(req, resp);
        }
    }

    private void forwardToLogin(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
