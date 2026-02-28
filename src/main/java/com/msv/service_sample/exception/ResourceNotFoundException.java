package com.msv.service_sample.exception;

import com.msv.service_sample.constant.ErrorCode;

// ── Resource Not Found ────────────────────────────────────────────
public class ResourceNotFoundException extends BusinessException {

    public ResourceNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

    public ResourceNotFoundException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public static ResourceNotFoundException invoice(String id) {
        return new ResourceNotFoundException(
                ErrorCode.INV_INVOICE_NOT_FOUND,
                "Invoice not found with id: " + id
        );
    }

    public static ResourceNotFoundException customer(String id) {
        return new ResourceNotFoundException(
                ErrorCode.INV_CUSTOMER_NOT_FOUND,
                "Customer not found with id: " + id
        );
    }

    public static ResourceNotFoundException merchant(String id) {
        return new ResourceNotFoundException(
                ErrorCode.INV_MERCHANT_NOT_FOUND,
                "Merchant not found with id: " + id
        );
    }
}