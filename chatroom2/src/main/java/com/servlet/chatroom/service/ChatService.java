package com.servlet.chatroom.service;

import com.servlet.chatroom.entity.ChatMessage;
import java.util.*;
import java.util.stream.Collectors;

public class ChatService {
    private static final List<ChatMessage> messages = new ArrayList<>();

    private ChatService() {}

    public static void addMessage(ChatMessage message) {
        messages.add(message);
    }

    public static void addSystemMessage(String content) {
        ChatMessage msg = new ChatMessage("系统", content);
        msg.setSystem(true);
        messages.add(msg);
    }

    public static List<ChatMessage> getMessagesForUser(String currentUser) {
        return messages.stream()
                .filter(msg ->
                        msg.isSystem() ||
                                msg.getTargetUser() == null ||
                                currentUser.equals(msg.getSender()) ||
                                currentUser.equals(msg.getTargetUser())
                )
                .collect(Collectors.toList());
    }

    public static boolean isValidPrivateTarget(String target) {
        return new UserService().userExists(target);
    }
}