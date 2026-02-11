package com.smartbilling.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public class BillRequest {
  @NotNull
  private Long customerId;
  @NotNull
  private BigDecimal discount;
  @Valid
  @NotEmpty
  private List<BillItemRequest> items;

  public Long getCustomerId() { return customerId; }
  public void setCustomerId(Long customerId) { this.customerId = customerId; }
  public BigDecimal getDiscount() { return discount; }
  public void setDiscount(BigDecimal discount) { this.discount = discount; }
  public List<BillItemRequest> getItems() { return items; }
  public void setItems(List<BillItemRequest> items) { this.items = items; }
}
