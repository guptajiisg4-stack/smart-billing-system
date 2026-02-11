package com.smartbilling.service;

import com.smartbilling.dto.*;
import com.smartbilling.model.*;
import com.smartbilling.repository.BillRepository;
import com.smartbilling.repository.CustomerRepository;
import com.smartbilling.repository.ProductRepository;
import com.smartbilling.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class BillService {
  private final BillRepository billRepository;
  private final ProductRepository productRepository;
  private final CustomerRepository customerRepository;
  private final UserRepository userRepository;

  public BillService(BillRepository billRepository, ProductRepository productRepository, CustomerRepository customerRepository, UserRepository userRepository) {
    this.billRepository = billRepository;
    this.productRepository = productRepository;
    this.customerRepository = customerRepository;
    this.userRepository = userRepository;
  }

  @Transactional
  public BillResponse create(BillRequest request, HttpSession session) {
    Long userId = (Long) session.getAttribute("userId");
    if (userId == null) throw new IllegalStateException("Unauthorized");
    User user = userRepository.findById(userId).orElseThrow();
    Customer customer = customerRepository.findById(request.getCustomerId()).orElseThrow();

    Bill bill = new Bill();
    bill.setUser(user);
    bill.setCustomer(customer);
    bill.setDate(LocalDate.now());
    bill.setDiscount(request.getDiscount());

    BigDecimal amount = BigDecimal.ZERO;
    for (BillItemRequest reqItem : request.getItems()) {
      Product product = productRepository.findById(reqItem.getProductId()).orElseThrow();
      if (product.getQuantity() < reqItem.getQuantity()) throw new IllegalArgumentException("Insufficient stock");
      product.setQuantity(product.getQuantity() - reqItem.getQuantity());
      productRepository.save(product);

      BillItem item = new BillItem();
      item.setBill(bill);
      item.setProduct(product);
      item.setPrice(product.getPrice());
      item.setQuantity(reqItem.getQuantity());
      bill.getItems().add(item);
      amount = amount.add(product.getPrice().multiply(BigDecimal.valueOf(reqItem.getQuantity())));
    }

    bill.setAmount(amount);
    bill.setFinalAmount(amount.subtract(request.getDiscount()).max(BigDecimal.ZERO));

    customer.setTotalVisits(customer.getTotalVisits() + 1);
    customer.setLastVisit(LocalDate.now());
    customerRepository.save(customer);

    return map(billRepository.save(bill));
  }

  public BillResponse getById(Long id) { return map(billRepository.findById(id).orElseThrow()); }
  public List<BillResponse> getTodayBills() { return billRepository.findByDate(LocalDate.now()).stream().map(this::map).toList(); }

  private BillResponse map(Bill bill) {
    BillResponse response = new BillResponse();
    response.setBillId(bill.getBillId());
    response.setCustomerName(bill.getCustomer().getFirstName() + " " + bill.getCustomer().getLastName());
    response.setDate(bill.getDate());
    response.setAmount(bill.getAmount());
    response.setDiscount(bill.getDiscount());
    response.setFinalAmount(bill.getFinalAmount());
    response.setItems(bill.getItems().stream().map(item -> new BillItemResponse(item.getProduct().getProductName(), item.getPrice(), item.getQuantity())).toList());
    return response;
  }
}
