package com.servlet.forum.controller;

import com.servlet.forum.service.PostService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private PostService postService;

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        if (username == null) return "redirect:/login";

        model.addAttribute("username", username);
        model.addAttribute("posts", postService.getAllPosts());
        return "home";
    }

    @PostMapping("/home")
    public String createPost(@RequestParam String title,
                             @RequestParam String content,
                             HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) return "redirect:/login";

        postService.createPost(title.trim(), content.trim(), username);
        return "redirect:/home";
    }

    @GetMapping("/")
    public String rootRedirect(HttpSession session) {
        return session.getAttribute("username") != null ? "redirect:/home" : "redirect:/login";
    }
}
