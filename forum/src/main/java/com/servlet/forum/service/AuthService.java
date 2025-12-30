package com.servlet.forum.service;

import com.servlet.forum.model.User;
import org.springframework.stereotype.Service;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AuthService {
    private final ConcurrentHashMap<String, User> users = new ConcurrentHashMap<>();

    public boolean register(String username, String password) {
        return users.putIfAbsent(username, new User(username, password)) == null;
    }

    public boolean validateCredentials(String username, String password) {
        User user = users.get(username);
        return user != null && user.getPassword().equals(password);
    }

    public boolean userExists(String username) {
        return users.containsKey(username);
    }
}