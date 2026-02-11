package com.smartbilling.controller;

import com.smartbilling.dto.BillRequest;
import com.smartbilling.dto.BillResponse;
import com.smartbilling.service.BillService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bills")
public class BillController {
  private final BillService billService;

  public BillController(BillService billService) { this.billService = billService; }

  @PostMapping
  public BillResponse create(@Valid @RequestBody BillRequest request, HttpSession session) { return billService.create(request, session); }
  @GetMapping("/{id}")
  public BillResponse getById(@PathVariable Long id) { return billService.getById(id); }
  @GetMapping("/today")
  public List<BillResponse> today() { return billService.getTodayBills(); }
}
