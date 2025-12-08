package com.servlet.secondhand.controller;

import com.servlet.secondhand.service.ItemService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    private ItemService itemService = new ItemService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String keyword = req.getParameter("q");
            if (keyword == null) keyword = "";
            List<?> items = itemService.searchItems(keyword.trim());
            req.setAttribute("items", items);
            req.getRequestDispatcher("/search-results.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("dashboard.jsp?error=search");
        }
    }
}