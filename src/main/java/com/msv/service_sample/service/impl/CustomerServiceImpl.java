package com.msv.service_sample.service.impl;

import com.msv.service_sample.constant.ErrorCode;
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

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerServiceImpl{

    private final CustomerRepository repo;

    // Get List
    public List<Customer> getList() {
        return repo.findAll();
    }


//    private final CustomerRepository repo;

//    @Override
//    public CustomerResponse create(CustomerRequest req) {
//        String email = normalizeEmail(req.email());
//        String phone = normalizePhone(req.phoneNumber());
//
//        if (repo.existsByEmail(email)) {
//            //throw new BusinessException(ErrorCode.INV_CUSTOMER_EMAIL_ALREADY_EXISTS);
//        }
//        if (repo.existsByPhoneNumber(phone)) {
//            //throw new BusinessException(ErrorCode.INV_CUSTOMER_PHONE_ALREADY_EXISTS);
//        }
//
//        Customer c = Customer.builder()
//                .firstName(req.firstName().trim())
//                .lastName(req.lastName().trim())
//                .email(email)
//                .phoneNumber(phone)
//                .note(req.note())
//                .build();
//
//        return toResponse(repo.save(c));
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public CustomerResponse getById(Long id) {
//        return toResponse(findCustomer(id));
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public Page<CustomerResponse> list(Pageable pageable) {
//        return repo.findAll(pageable).map(this::toResponse);
//    }
//
//    @Override
//    public CustomerResponse update(Long id, CustomerRequest req) {
//        Customer c = findCustomer(id);
//
//        String email = normalizeEmail(req.email());
//        String phone = normalizePhone(req.phoneNumber());
//
//        if (!email.equals(c.getEmail()) && repo.existsByEmail(email)) {
//            //throw new BusinessException(ErrorCode.INV_CUSTOMER_EMAIL_ALREADY_EXISTS);
//        }
//        if (!phone.equals(c.getPhoneNumber()) && repo.existsByPhoneNumber(phone)) {
//            //throw new BusinessException(ErrorCode.INV_CUSTOMER_PHONE_ALREADY_EXISTS);
//        }
//
//        c.setFirstName(req.firstName().trim());
//        c.setLastName(req.lastName().trim());
//        c.setEmail(email);
//        c.setPhoneNumber(phone);
//        c.setNote(req.note());
//
//        return toResponse(repo.save(c));
//    }
//
//    @Override
//    public void delete(Long id) {
//        Customer c = findCustomer(id);
//        repo.delete(c);
//    }
}