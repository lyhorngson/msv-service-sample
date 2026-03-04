package com.msv.service_sample.dto.response;



import java.time.Instant;

public record CustomerResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String note,
        Instant createdAt,
        Instant updatedAt,
        String createdBy,
        String updatedBy
) {}