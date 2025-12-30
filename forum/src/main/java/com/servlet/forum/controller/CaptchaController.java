package com.servlet.forum.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@Controller
public class CaptchaController {

    private static final char[] CHARS = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789".toCharArray();
    private static final Random random = new Random();

    @GetMapping("/captcha")
    public void captcha(HttpSession session, HttpServletResponse response) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            sb.append(CHARS[random.nextInt(CHARS.length)]);
        }
        String captcha = sb.toString();
        session.setAttribute("captcha", captcha);

        int width = 100, height = 40;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Dialog", Font.BOLD, 24));
        g.setColor(Color.BLACK);
        g.drawString(captcha, 20, 30);

        g.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i < 5; i++) {
            int x1 = random.nextInt(width), y1 = random.nextInt(height);
            int x2 = random.nextInt(width), y2 = random.nextInt(height);
            g.drawLine(x1, y1, x2, y2);
        }
        g.dispose();

        response.setContentType("image/png");
        ImageIO.write(image, "png", response.getOutputStream());
    }
}
