package com.servlet.chatroom.controller;

import com.servlet.chatroom.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            req.setAttribute("error", "用户名和密码不能为空");
            forwardToRegister(req, resp);
            return;
        }

        if (userService.register(username, password)) {
            req.setAttribute("success", "注册成功！请登录。");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } else {
            req.setAttribute("error", "用户名已存在");
            forwardToRegister(req, resp);
        }
    }

    private void forwardToRegister(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}



