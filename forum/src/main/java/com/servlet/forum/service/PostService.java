package com.servlet.forum.service;

import com.servlet.forum.model.Post;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PostService {
    private final List<Post> posts = new CopyOnWriteArrayList<>();
    private final AtomicLong nextId = new AtomicLong(1);

    public void createPost(String title, String content, String author) {
        posts.add(new Post(nextId.getAndIncrement(), title, content, author));
    }

    public List<Post> getAllPosts() {
        return posts;
    }

    public Post getPostById(Long id) {
        return posts.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }

    public void addReply(Long postId, String replyContent, String author) {
        Post post = getPostById(postId);
        if (post != null) {
            post.addReply(replyContent, author);
        }
    }
}
