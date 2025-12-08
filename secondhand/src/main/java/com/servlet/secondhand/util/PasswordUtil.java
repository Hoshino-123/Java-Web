package com.servlet.secondhand.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordUtil {

    private static final int SALT_LENGTH = 32;
    private static final int HASH_ITERATIONS = 10000;


    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }


    public static String hashPassword(String password, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);
            byte[] saltBytes = Base64.getDecoder().decode(salt);


            byte[] data = new byte[passwordBytes.length + saltBytes.length];
            System.arraycopy(passwordBytes, 0, data, 0, passwordBytes.length);
            System.arraycopy(saltBytes, 0, data, passwordBytes.length, saltBytes.length);


            byte[] hash = data;
            for (int i = 0; i < HASH_ITERATIONS; i++) {
                hash = md.digest(hash);
                md.reset();
            }

            return bytesToHex(hash);
        } catch (Exception e) {
            throw new RuntimeException("密码哈希失败", e);
        }
    }


    public static boolean verifyPassword(String password, String storedHashWithSalt) {
        String[] parts = storedHashWithSalt.split(":", 2);
        if (parts.length != 2) return false;
        String salt = parts[0];
        String expectedHash = parts[1];
        String actualHash = hashPassword(password, salt);
        return expectedHash.equals(actualHash);
    }


    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static String encodePassword(String password) {
        String salt = generateSalt();
        String hash = hashPassword(password, salt);
        return salt + ":" + hash;
    }
}