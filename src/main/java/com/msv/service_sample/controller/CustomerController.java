package com.msv.service_sample.controller;

import com.msv.service_sample.dto.common.ApiResponse;
import com.msv.service_sample.dto.response.CustomerResponse;
import com.msv.service_sample.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<CustomerResponse>>> listCustomers(Pageable pageable) {
        Page<CustomerResponse> customers = customerService.list(pageable);
        return ResponseEntity.ok(ApiResponse.ok(
                customers,
                "Successfully retrieved customers"
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerResponse>> getCustomer(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(
                customerService.getById(id),
                "Customer found"
        ));
    }
}