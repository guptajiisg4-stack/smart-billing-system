package com.smartbilling.service;

import com.smartbilling.model.Customer;
import com.smartbilling.repository.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
  private final CustomerRepository customerRepository;

  public CustomerService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  public Page<Customer> findAll(Pageable pageable) { return customerRepository.findAll(pageable); }
  public Customer create(Customer customer) {
    if (customer.getTotalVisits() == null) customer.setTotalVisits(0);
    return customerRepository.save(customer);
  }
  public Customer update(Long id, Customer customer) {
    Customer existing = customerRepository.findById(id).orElseThrow();
    existing.setFirstName(customer.getFirstName());
    existing.setMiddleName(customer.getMiddleName());
    existing.setLastName(customer.getLastName());
    existing.setPhoneNumber(customer.getPhoneNumber());
    return customerRepository.save(existing);
  }
  public void delete(Long id) { customerRepository.deleteById(id); }
}
