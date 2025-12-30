package com.servlet.forum.model;

import lombok.Data;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data
public class Post {
    private Long id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime createdAt;
    private List<Reply> replies;

    // 无参构造器（必需！）
    public Post() {}

    // 有参构造器
    public Post(Long id, String title, String content, String author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.createdAt = LocalDateTime.now();
        this.replies = new ArrayList<>();
    }

    public String getFormattedCreatedAt() {
        return createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public void addReply(String content, String author) {
        this.replies.add(new Reply(content, author));
    }
}
