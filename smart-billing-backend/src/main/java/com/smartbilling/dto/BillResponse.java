package com.smartbilling.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class BillResponse {
  private Long billId;
  private String customerName;
  private LocalDate date;
  private BigDecimal amount;
  private BigDecimal discount;
  private BigDecimal finalAmount;
  private List<BillItemResponse> items;

  public Long getBillId() { return billId; }
  public void setBillId(Long billId) { this.billId = billId; }
  public String getCustomerName() { return customerName; }
  public void setCustomerName(String customerName) { this.customerName = customerName; }
  public LocalDate getDate() { return date; }
  public void setDate(LocalDate date) { this.date = date; }
  public BigDecimal getAmount() { return amount; }
  public void setAmount(BigDecimal amount) { this.amount = amount; }
  public BigDecimal getDiscount() { return discount; }
  public void setDiscount(BigDecimal discount) { this.discount = discount; }
  public BigDecimal getFinalAmount() { return finalAmount; }
  public void setFinalAmount(BigDecimal finalAmount) { this.finalAmount = finalAmount; }
  public List<BillItemResponse> getItems() { return items; }
  public void setItems(List<BillItemResponse> items) { this.items = items; }
}
