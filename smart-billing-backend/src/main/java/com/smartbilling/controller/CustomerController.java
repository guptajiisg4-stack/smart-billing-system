package com.smartbilling.controller;

import com.smartbilling.model.Customer;
import com.smartbilling.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
  private final CustomerService customerService;

  public CustomerController(CustomerService customerService) { this.customerService = customerService; }

  @GetMapping
  public Page<Customer> getAll(Pageable pageable) { return customerService.findAll(pageable); }
  @PostMapping
  public Customer create(@RequestBody Customer customer) { return customerService.create(customer); }
  @PutMapping("/{id}")
  public Customer update(@PathVariable Long id, @RequestBody Customer customer) { return customerService.update(id, customer); }
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) { customerService.delete(id); return ResponseEntity.noContent().build(); }
}
