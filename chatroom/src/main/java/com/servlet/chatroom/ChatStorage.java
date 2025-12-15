package com.servlet.chatroom;

import java.util.ArrayList;
import java.util.List;

public class ChatStorage {
    private static final List<ChatMessage> messages = new ArrayList<>();

    static {
        // 添加默认消息
        messages.add(new ChatMessage("系统消息", "欢迎来到在线聊天室！", System.currentTimeMillis()));
    }

    public static void addMessage(String username, String content) {
        messages.add(new ChatMessage(username, content, System.currentTimeMillis()));
    }

    public static List<ChatMessage> getMessages() {
        return new ArrayList<>(messages);
    }

    public static class ChatMessage {
        private String username;
        private String content;
        private long timestamp;

        public ChatMessage(String username, String content, long timestamp) {
            this.username = username;
            this.content = content;
            this.timestamp = timestamp;
        }

        public String getUsername() {
            return username;
        }

        public String getContent() {
            return content;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public String getFormattedTime() {
            return new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date(timestamp));
        }
    }
}