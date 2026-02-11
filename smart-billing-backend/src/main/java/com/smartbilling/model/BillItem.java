package com.smartbilling.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "bill_items")
public class BillItem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long billItemId;
  @ManyToOne(optional = false)
  @JoinColumn(name = "bill_id")
  private Bill bill;
  @ManyToOne(optional = false)
  @JoinColumn(name = "product_id")
  private Product product;
  @Column(nullable = false)
  private BigDecimal price;
  @Column(nullable = false)
  private Integer quantity;

  public Long getBillItemId() { return billItemId; }
  public void setBillItemId(Long billItemId) { this.billItemId = billItemId; }
  public Bill getBill() { return bill; }
  public void setBill(Bill bill) { this.bill = bill; }
  public Product getProduct() { return product; }
  public void setProduct(Product product) { this.product = product; }
  public BigDecimal getPrice() { return price; }
  public void setPrice(BigDecimal price) { this.price = price; }
  public Integer getQuantity() { return quantity; }
  public void setQuantity(Integer quantity) { this.quantity = quantity; }
}
