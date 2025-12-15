package com.servlet.chatroom.util;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/chat","/chat.jsp","/sendMessage"})
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        if (req.getSession().getAttribute("username") == null) {
            ((HttpServletResponse) response).sendRedirect(req.getContextPath() + "/index.jsp");
        } else {
            chain.doFilter(request, response);
        }
    }
}
