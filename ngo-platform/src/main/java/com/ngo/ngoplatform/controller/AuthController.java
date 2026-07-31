package com.ngo.ngoplatform.controller;

import com.ngo.ngoplatform.dto.AuthRequest;
import com.ngo.ngoplatform.dto.AuthResponse;
import com.ngo.ngoplatform.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {

    private final AuthService authService;
    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {

        System.out.println("LOGIN API HIT");

        return authService.login(request);
    }
}