package com.smartbilling.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long productId;
  @Column(nullable = false)
  private String productName;
  @Column(nullable = false)
  private String category;
  @Column(nullable = false)
  private BigDecimal price;
  @Column(nullable = false)
  private Integer quantity;

  public Long getProductId() { return productId; }
  public void setProductId(Long productId) { this.productId = productId; }
  public String getProductName() { return productName; }
  public void setProductName(String productName) { this.productName = productName; }
  public String getCategory() { return category; }
  public void setCategory(String category) { this.category = category; }
  public BigDecimal getPrice() { return price; }
  public void setPrice(BigDecimal price) { this.price = price; }
  public Integer getQuantity() { return quantity; }
  public void setQuantity(Integer quantity) { this.quantity = quantity; }
}
