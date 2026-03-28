package com.msv.service_sample.service.impl;

import com.msv.service_sample.domain.entity.Customer;
import com.msv.service_sample.dto.request.CustomerRequest;
import com.msv.service_sample.dto.response.CustomerResponse;
import com.msv.service_sample.repository.CustomerRepository;
import com.msv.service_sample.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repo;

    @Override
    public CustomerResponse create(CustomerRequest req) {
        Customer c = new Customer();
        c.setFirstName(req.firstName().trim());
        c.setLastName(req.lastName().trim());
        c.setEmail(req.email().toLowerCase().trim());
        c.setPhoneNumber(req.phoneNumber());
        c.setNote(req.note());
        // createdBy/updatedBy are usually handled by Spring Security AuditorAware
        // but can be set manually here if needed: c.setCreatedBy("system");

        return toResponse(repo.save(c));
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerResponse getById(Long id) {
        return toResponse(findCustomer(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CustomerResponse> list(Pageable pageable) {
        return repo.findAll(pageable).map(this::toResponse);
    }

    @Override
    public CustomerResponse update(Long id, CustomerRequest req) {
        Customer c = findCustomer(id);
        c.setFirstName(req.firstName().trim());
        c.setLastName(req.lastName().trim());
        c.setEmail(req.email().toLowerCase().trim());
        c.setPhoneNumber(req.phoneNumber());
        c.setNote(req.note());
        return toResponse(repo.save(c));
    }

    @Override
    public void delete(Long id) {
        repo.delete(findCustomer(id));
    }

    private Customer findCustomer(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
    }

    private CustomerResponse toResponse(Customer c) {
        return new CustomerResponse(
                c.getId(),
                c.getFirstName(),
                c.getLastName(),
                c.getEmail(),
                c.getPhoneNumber(),
                c.getNote(),
                c.getCreatedAt() != null ? c.getCreatedAt() : null,
                c.getUpdatedAt() != null ? c.getUpdatedAt() : null,
                c.getCreatedBy(),
                c.getUpdatedBy()
        );
    }
}