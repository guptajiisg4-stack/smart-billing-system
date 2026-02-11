package com.smartbilling.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;
  @Column(unique = true, nullable = false)
  private String username;
  @Column(nullable = false)
  private String password;
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private UserRole role;
  private BigDecimal salary;

  public Long getUserId() { return userId; }
  public void setUserId(Long userId) { this.userId = userId; }
  public String getUsername() { return username; }
  public void setUsername(String username) { this.username = username; }
  public String getPassword() { return password; }
  public void setPassword(String password) { this.password = password; }
  public UserRole getRole() { return role; }
  public void setRole(UserRole role) { this.role = role; }
  public BigDecimal getSalary() { return salary; }
  public void setSalary(BigDecimal salary) { this.salary = salary; }
}
