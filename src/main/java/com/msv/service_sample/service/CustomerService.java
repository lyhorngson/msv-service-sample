package com.msv.service_sample.service;

import com.msv.service_sample.dto.request.CustomerRequest;
import com.msv.service_sample.dto.response.CustomerResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {
    CustomerResponse create(CustomerRequest request);



    CustomerResponse getById(Long id);
    Page<CustomerResponse> list(Pageable pageable);
    CustomerResponse update(Long id, CustomerRequest request);
    void delete(Long id);
}
