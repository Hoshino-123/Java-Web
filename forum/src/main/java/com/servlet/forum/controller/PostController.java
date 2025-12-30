package com.servlet.forum.controller;

import com.servlet.forum.model.Post;
import com.servlet.forum.service.PostService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/post/{id}")
    public String viewPost(@PathVariable Long id, HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login";
        }

        Post post = postService.getPostById(id);
        if (post == null || post.getId() == null) {
            return "redirect:/home"; // 或跳转到错误页
        }

        model.addAttribute("post", post);
        return "post-detail";
    }

    @PostMapping("/post/{id}")
    public String addReply(@PathVariable Long id,
                           @RequestParam String reply,
                           HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) return "redirect:/login";

        postService.addReply(id, reply.trim(), username);
        return "redirect:/post/" + id;
    }
}
