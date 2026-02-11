package com.smartbilling.controller;

import com.smartbilling.model.Product;
import com.smartbilling.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {
  private final ProductService productService;

  public ProductController(ProductService productService) { this.productService = productService; }

  @GetMapping
  public Page<Product> getAll(Pageable pageable) { return productService.findAll(pageable); }
  @PostMapping
  public Product create(@RequestBody Product product) { return productService.create(product); }
  @PutMapping("/{id}")
  public Product update(@PathVariable Long id, @RequestBody Product product) { return productService.update(id, product); }
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) { productService.delete(id); return ResponseEntity.noContent().build(); }
}
