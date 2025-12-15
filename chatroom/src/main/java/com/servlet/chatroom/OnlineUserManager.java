// src/main/java/com/servlet/chatroom/OnlineUserManager.java
package com.servlet.chatroom;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class OnlineUserManager {
    private static final Set<String> onlineUsers = Collections.synchronizedSet(new HashSet<>());

    public static void addUser(String username) {
        onlineUsers.add(username);
    }

    public static void removeUser(String username) {
        onlineUsers.remove(username);
    }

    public static int getOnlineCount() {
        return onlineUsers.size();
    }

    public static Set<String> getOnlineUsers() {
        return new HashSet<>(onlineUsers);
    }
}
