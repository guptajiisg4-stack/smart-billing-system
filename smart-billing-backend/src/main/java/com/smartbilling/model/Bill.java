package com.smartbilling.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bills")
public class Bill {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long billId;
  @ManyToOne(optional = false)
  @JoinColumn(name = "customer_id")
  private Customer customer;
  @ManyToOne(optional = false)
  @JoinColumn(name = "user_id")
  private User user;
  @Column(nullable = false)
  private LocalDate date;
  @Column(nullable = false)
  private BigDecimal amount;
  @Column(nullable = false)
  private BigDecimal discount;
  @Column(nullable = false)
  private BigDecimal finalAmount;
  @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<BillItem> items = new ArrayList<>();

  public Long getBillId() { return billId; }
  public void setBillId(Long billId) { this.billId = billId; }
  public Customer getCustomer() { return customer; }
  public void setCustomer(Customer customer) { this.customer = customer; }
  public User getUser() { return user; }
  public void setUser(User user) { this.user = user; }
  public LocalDate getDate() { return date; }
  public void setDate(LocalDate date) { this.date = date; }
  public BigDecimal getAmount() { return amount; }
  public void setAmount(BigDecimal amount) { this.amount = amount; }
  public BigDecimal getDiscount() { return discount; }
  public void setDiscount(BigDecimal discount) { this.discount = discount; }
  public BigDecimal getFinalAmount() { return finalAmount; }
  public void setFinalAmount(BigDecimal finalAmount) { this.finalAmount = finalAmount; }
  public List<BillItem> getItems() { return items; }
  public void setItems(List<BillItem> items) { this.items = items; }
}
