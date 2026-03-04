package com.msv.service_sample.repository;

import com.msv.service_sample.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
//    boolean existsByEmail(String email);
//    boolean existsByPhoneNumber(String phoneNumber);
//
//    Optional<Customer> findByEmail(String email);




}