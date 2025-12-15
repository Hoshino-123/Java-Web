package com.servlet.chatroom.service;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class OnlineUserService {
    private static final Set<String> onlineUsers = ConcurrentHashMap.newKeySet();

    private OnlineUserService() {}

    public static void addUser(String username) {
        if (username != null && !username.trim().isEmpty()) {
            onlineUsers.add(username.trim());
        }
    }

    public static void removeUser(String username) {
        if (username != null) {
            onlineUsers.remove(username);
        }
    }

    public static int getOnlineCount() {
        return onlineUsers.size();
    }

    public static Set<String> getOnlineUsers() {
        return new HashSet<>(onlineUsers);
    }

    public static boolean isUserOnline(String username) {
        return onlineUsers.contains(username);
    }
}