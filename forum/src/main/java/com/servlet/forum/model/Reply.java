package com.servlet.forum.model;

import lombok.Data;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class Reply {
    private String content;
    private String author;
    private LocalDateTime createdAt;

    // 无参构造器
    public Reply() {}

    public Reply(String content, String author) {
        this.content = content;
        this.author = author;
        this.createdAt = LocalDateTime.now();
    }

    public String getFormattedCreatedAt() {
        return createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}