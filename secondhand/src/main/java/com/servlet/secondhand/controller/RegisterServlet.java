package com.servlet.secondhand.controller;

import com.servlet.secondhand.service.UserService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (userService.register(username, password)) {
            resp.sendRedirect("index.jsp?registered=1");
        } else {
            resp.sendRedirect("register.jsp?error=1");
        }
    }
}
