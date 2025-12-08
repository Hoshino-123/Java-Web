package com.servlet.secondhand.service;

import com.servlet.secondhand.dao.UserDao;
import com.servlet.secondhand.entity.User;
import com.servlet.secondhand.util.PasswordUtil;

public class UserService {
    private UserDao userDao = new UserDao();

    public boolean register(String username, String password) {
        if (userDao.findByUsername(username) != null) {
            return false;
        }
        String hash = PasswordUtil.encodePassword(password);
        User user = new User();
        user.setUsername(username);
        user.setPasswordHash(hash);
        userDao.save(user);
        return true;
    }

    public User authenticate(String username, String password) {
        User user = userDao.findByUsername(username);
        if (user != null && PasswordUtil.verifyPassword(password, user.getPasswordHash())) {
            return user;
        }
        return null;
    }
}