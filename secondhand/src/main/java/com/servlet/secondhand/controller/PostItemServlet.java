package com.servlet.secondhand.controller;

import com.servlet.secondhand.entity.Item;
import com.servlet.secondhand.entity.User;
import com.servlet.secondhand.service.ItemService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/post-item")
public class PostItemServlet extends HttpServlet {
    private ItemService itemService = new ItemService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            resp.sendRedirect("index.jsp");
            return;
        }

        String title = req.getParameter("title");
        String description = req.getParameter("description");
        double price = Double.parseDouble(req.getParameter("price"));

        Item item = new Item();
        item.setTitle(title);
        item.setDescription(description);
        item.setPrice(price);
        item.setUserId(user.getId());

        itemService.save(item);
        resp.sendRedirect("dashboard?posted=1");
    }
}