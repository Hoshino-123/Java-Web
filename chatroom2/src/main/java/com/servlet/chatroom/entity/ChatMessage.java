package com.servlet.chatroom.entity;

public class ChatMessage {
    private String sender;
    private String content;
    private String targetUser;
    private boolean system = false;
    private String formattedTime;

    public ChatMessage(String sender, String content) {
        this.sender = sender;
        this.content = content;
        this.formattedTime = new java.text.SimpleDateFormat("HH:mm:ss")
                .format(new java.util.Date());
    }

    // 前端 JS 使用 getUsername()
    public String getUsername() {
        return sender;
    }

    // JSP 使用 getSender()
    public String getSender() {
        return sender;
    }

    public String getContent() {
        return content;
    }

    public String getTargetUser() {
        return targetUser;
    }

    public void setTargetUser(String targetUser) {
        this.targetUser = targetUser;
    }

    public boolean isSystem() {
        return system;
    }

    public void setSystem(boolean system) {
        this.system = system;
    }

    public String getFormattedTime() {
        return formattedTime;
    }
}