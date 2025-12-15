package com.servlet.chatroom;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (UserStorage.validateUser(username, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            OnlineUserManager.addUser(username);

            request.setAttribute("onlineCount", OnlineUserManager.getOnlineCount());
            request.getRequestDispatcher("/chat.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "用户名或密码错误");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
