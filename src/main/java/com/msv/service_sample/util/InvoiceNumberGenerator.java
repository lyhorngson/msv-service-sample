package com.msv.service_sample.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Generates unique invoice numbers per merchant.
 *
 * Format: INV-{YEAR}-{MERCHANT_ID}-{SEQ:06d}
 * Example: INV-2024-001-000042
 *
 * NOTE: For production with multiple instances, replace with
 * a database sequence or Redis counter. This in-memory version
 * is suitable for single-instance deployment.
 *
 * TODO (Next Sprint): Migrate to DB-backed InvoiceSequence table.
 */
@Component
@RequiredArgsConstructor
public class InvoiceNumberGenerator {

    // Per-merchant-per-year counters
    private final ConcurrentHashMap<String, AtomicLong> counters = new ConcurrentHashMap<>();

    public synchronized String next(Long merchantId) {
        int year = LocalDate.now().getYear();
        String key = merchantId + "-" + year;

        AtomicLong counter = counters.computeIfAbsent(key, k -> new AtomicLong(0));
        long seq = counter.incrementAndGet();

        String candidate = String.format("INV-%d-%03d-%06d", year, merchantId, seq);

        return candidate;
    }
}