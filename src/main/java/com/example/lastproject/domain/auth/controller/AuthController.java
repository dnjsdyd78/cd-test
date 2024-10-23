package com.example.lastproject.domain.auth.controller;

import com.example.lastproject.domain.auth.dto.request.SigninRequest;
import com.example.lastproject.domain.auth.dto.request.SignupRequest;
import com.example.lastproject.domain.auth.dto.response.SigninResponse;
import com.example.lastproject.domain.auth.dto.response.SignupResponse;
import com.example.lastproject.domain.auth.entity.AuthUser;
import com.example.lastproject.domain.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public SignupResponse signup(@Valid @RequestBody SignupRequest signupRequest) {
        return authService.signup(signupRequest);
    }

    @PostMapping("/signin")
    public SigninResponse signin(@Valid @RequestBody SigninRequest signinRequest) {
        return authService.signin(signinRequest);
    }

//    @GetMapping("/withdrawal") // 회원 탈퇴
//    public ResponseEntity<Void> withdrawal(@AuthenticationPrincipal AuthUser authUser) {
//        authService.withdrawal(authUser);
//        return ResponseEntity.ok().build();
//    }
}