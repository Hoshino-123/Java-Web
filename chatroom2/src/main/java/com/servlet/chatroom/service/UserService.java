package com.servlet.chatroom.service;

import java.util.HashMap;
import java.util.Map;

public class UserService {
    private static final Map<String, String> users = new HashMap<>();

    static {
        users.put("admin", "123456");
    }

    public boolean authenticate(String username, String password) {
        String pwd = users.get(username);
        return pwd != null && pwd.equals(password);
    }

    public boolean register(String username, String password) {
        if (users.containsKey(username)) {
            return false;
        }
        users.put(username, password);
        return true;
    }

    public boolean userExists(String username) {
        return users.containsKey(username);
    }
}