package com.smartbilling.service;

import com.smartbilling.model.Product;
import com.smartbilling.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
  private final ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) { this.productRepository = productRepository; }

  public Page<Product> findAll(Pageable pageable) { return productRepository.findAll(pageable); }
  public Product create(Product product) { return productRepository.save(product); }
  public Product update(Long id, Product product) {
    Product existing = productRepository.findById(id).orElseThrow();
    existing.setProductName(product.getProductName());
    existing.setCategory(product.getCategory());
    existing.setPrice(product.getPrice());
    existing.setQuantity(product.getQuantity());
    return productRepository.save(existing);
  }
  public void delete(Long id) { productRepository.deleteById(id); }
}
