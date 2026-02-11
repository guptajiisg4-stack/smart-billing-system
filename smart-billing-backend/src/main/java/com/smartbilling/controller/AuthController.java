package com.smartbilling.controller;

import com.smartbilling.dto.LoginRequest;
import com.smartbilling.service.AuthService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {
  private final AuthService authService;

  public AuthController(AuthService authService) { this.authService = authService; }

  @PostMapping("/login")
  public ResponseEntity<Void> login(@Valid @RequestBody LoginRequest request, HttpSession session) {
    authService.login(request, session);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/logout")
  public ResponseEntity<Void> logout(HttpSession session) {
    authService.logout(session);
    return ResponseEntity.ok().build();
  }
}
