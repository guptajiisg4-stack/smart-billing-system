package com.smartbilling.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "customers")
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long customerId;
  @Column(nullable = false)
  private String firstName;
  private String middleName;
  @Column(nullable = false)
  private String lastName;
  @Column(nullable = false)
  private String phoneNumber;
  @Column(nullable = false)
  private Integer totalVisits = 0;
  private LocalDate lastVisit;

  public Long getCustomerId() { return customerId; }
  public void setCustomerId(Long customerId) { this.customerId = customerId; }
  public String getFirstName() { return firstName; }
  public void setFirstName(String firstName) { this.firstName = firstName; }
  public String getMiddleName() { return middleName; }
  public void setMiddleName(String middleName) { this.middleName = middleName; }
  public String getLastName() { return lastName; }
  public void setLastName(String lastName) { this.lastName = lastName; }
  public String getPhoneNumber() { return phoneNumber; }
  public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
  public Integer getTotalVisits() { return totalVisits; }
  public void setTotalVisits(Integer totalVisits) { this.totalVisits = totalVisits; }
  public LocalDate getLastVisit() { return lastVisit; }
  public void setLastVisit(LocalDate lastVisit) { this.lastVisit = lastVisit; }
}
