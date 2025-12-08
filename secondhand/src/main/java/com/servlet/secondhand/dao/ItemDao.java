package com.servlet.secondhand.dao;

import com.servlet.secondhand.entity.Item;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ItemDao {
  private Dao dao = new Dao();

    public void save(Item item) {
        String sql = "INSERT INTO items (title, description, price, user_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = dao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, item.getTitle());
            ps.setString(2, item.getDescription());
            ps.setDouble(3, item.getPrice());
            ps.setInt(4, item.getUserId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Item> searchByKeyword(String keyword) {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT id, title, description, price, user_id FROM items WHERE title LIKE ?";
        try (Connection conn = dao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Item item = new Item();
                item.setId(rs.getInt("id"));
                item.setTitle(rs.getString("title"));
                item.setDescription(rs.getString("description"));
                item.setPrice(rs.getDouble("price"));
                item.setUserId(rs.getInt("user_id"));
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public List<Item> findByUserId(int userId) {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT id, title, description, price, user_id FROM items WHERE user_id = ? ORDER BY id DESC";
        try (Connection conn = dao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Item item = new Item();
                item.setId(rs.getInt("id"));
                item.setTitle(rs.getString("title"));
                item.setDescription(rs.getString("description"));
                item.setPrice(rs.getDouble("price"));
                item.setUserId(rs.getInt("user_id"));
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public List<Item> findAll() {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT id, title, description, price, user_id FROM items ORDER BY created_at DESC";
        try (Connection conn = dao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Item item = new Item();
                item.setId(rs.getInt("id"));
                item.setTitle(rs.getString("title"));
                item.setDescription(rs.getString("description"));
                item.setPrice(rs.getBigDecimal("price").doubleValue()); // 或直接用 double
                item.setUserId(rs.getInt("user_id"));
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    // ItemDao.java
    public boolean deleteById(int id) {
        String sql = "DELETE FROM items WHERE id = ?";
        try (Connection conn = dao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}