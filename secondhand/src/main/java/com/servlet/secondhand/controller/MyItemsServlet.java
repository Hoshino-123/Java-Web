// com/servlet/secondhand/controller/MyItemsServlet.java
package com.servlet.secondhand.controller;

import com.servlet.secondhand.entity.Item;
import com.servlet.secondhand.entity.User;
import com.servlet.secondhand.service.ItemService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/myitems")
public class MyItemsServlet extends HttpServlet {
    private ItemService itemService = new ItemService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            resp.sendRedirect("index.jsp");
            return;
        }

        // 查询当前用户发布的物品
        List<Item> myItems = itemService.getItemsByUserId(user.getId());
        req.setAttribute("myItems", myItems);
        req.getRequestDispatcher("/myitems.jsp").forward(req, resp);
    }
}