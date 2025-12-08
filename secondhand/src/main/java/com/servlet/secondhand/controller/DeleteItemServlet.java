// com/servlet/secondhand/controller/DeleteItemServlet.java
package com.servlet.secondhand.controller;

import com.servlet.secondhand.entity.User;
import com.servlet.secondhand.service.ItemService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/delete-item")
public class DeleteItemServlet extends HttpServlet {
    private ItemService itemService = new ItemService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            resp.sendRedirect("index.jsp");
            return;
        }

        int itemId = Integer.parseInt(req.getParameter("itemId"));


        boolean success = itemService.deleteItem(itemId);
        if (success) {
            resp.sendRedirect("myitems?deleted=1");
        } else {
            resp.sendRedirect("myitems?error=1");
        }
    }
}