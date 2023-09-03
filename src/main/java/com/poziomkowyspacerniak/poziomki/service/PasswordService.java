package com.poziomkowyspacerniak.poziomki.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordService {
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public boolean isPasswordMatch(String password, String hashedPassword) {
        return passwordEncoder.matches(password, hashedPassword);
    }
}
