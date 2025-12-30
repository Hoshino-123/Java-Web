package com.servlet.forum.controller;

import com.servlet.forum.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/register")
    public String showRegister() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           Model model) {
        if (authService.userExists(username)) {
            model.addAttribute("error", "用户名已存在");
            return "register";
        }
        authService.register(username, password);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLogin(HttpSession session) {
        if (session.getAttribute("username") != null) {
            return "redirect:/home";
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        @RequestParam String captcha,
                        HttpSession session,
                        Model model) {
        String storedCaptcha = (String) session.getAttribute("captcha");
        if (storedCaptcha == null || !storedCaptcha.equalsIgnoreCase(captcha)) {
            model.addAttribute("error", "验证码错误");
            return "login";
        }

        if (authService.validateCredentials(username, password)) {
            session.setAttribute("username", username);
            return "redirect:/home";
        } else {
            model.addAttribute("error", "用户名或密码错误");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
