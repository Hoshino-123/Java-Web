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

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    private ItemService itemService = new ItemService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Item> allItems = itemService.getAllItems();
        req.setAttribute("items", allItems);
        req.getRequestDispatcher("/dashboard.jsp").forward(req, resp);
    }
}