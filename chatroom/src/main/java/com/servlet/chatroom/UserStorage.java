package com.servlet.chatroom;

import java.util.HashMap;
import java.util.Map;

public class UserStorage {
    private static final Map<String, String> users = new HashMap<>();

    static {
        // 添加默认用户
        users.put("admin", "123456");
    }

    public static boolean registerUser(String username, String password) {
        if (users.containsKey(username)) {
            return false; // 用户名已存在
        }
        users.put(username, password);
        return true;
    }

    public static boolean validateUser(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }

    public static boolean userExists(String username) {
        return users.containsKey(username);
    }

    public static Map<String, String> getAllUsers() {
        return new HashMap<>(users);
    }
}