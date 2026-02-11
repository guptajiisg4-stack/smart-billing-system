package com.smartbilling.service;

import com.smartbilling.dto.LoginRequest;
import com.smartbilling.model.User;
import com.smartbilling.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
  private final UserRepository userRepository;
  private final BCryptPasswordEncoder encoder;

  public AuthService(UserRepository userRepository, BCryptPasswordEncoder encoder) {
    this.userRepository = userRepository;
    this.encoder = encoder;
  }

  public void login(LoginRequest request, HttpSession session) {
    User user = userRepository.findByUsername(request.getUsername())
      .filter(found -> encoder.matches(request.getPassword(), found.getPassword()))
      .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));
    session.setAttribute("userId", user.getUserId());
    session.setAttribute("role", user.getRole().name());
  }

  public void logout(HttpSession session) { session.invalidate(); }
}
