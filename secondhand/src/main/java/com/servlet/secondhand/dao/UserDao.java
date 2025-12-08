package com.servlet.secondhand.dao;

import com.servlet.secondhand.entity.User;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class UserDao {
  private Dao dao = new Dao();

    public User findByUsername(String username) {
        String sql = "SELECT id, username, password_hash FROM users WHERE username = ?";
        try (Connection conn = dao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPasswordHash(rs.getString("password_hash"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void save(User user) {
        String sql = "INSERT INTO users (username, password_hash) VALUES (?, ?)";
        try (Connection conn = dao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPasswordHash());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
