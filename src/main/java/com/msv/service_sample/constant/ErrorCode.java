package com.msv.service_sample.constant;

import org.springframework.http.HttpStatus;

/**
 * Standardized error codes for the MSV Invoice Service.
 *
 * Format: {SERVICE}_{DOMAIN}_{REASON}
 * Example: INV_INVOICE_NOT_FOUND
 *
 * These codes are returned in ApiErrorResponse code so clients
 * can programmatically handle errors without parsing messages.
 */
public enum ErrorCode {

    // ── Generic ──────────────────────────────────────────────────
    INTERNAL_SERVER_ERROR       (HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred"),
    VALIDATION_FAILED           (HttpStatus.BAD_REQUEST,           "Request validation failed"),
    INVALID_REQUEST             (HttpStatus.BAD_REQUEST,           "Invalid request parameters"),
    RESOURCE_NOT_FOUND          (HttpStatus.NOT_FOUND,             "Resource not found"),
    CONFLICT                    (HttpStatus.CONFLICT,              "Resource already exists"),
    METHOD_NOT_ALLOWED          (HttpStatus.METHOD_NOT_ALLOWED,    "HTTP method not allowed"),
    UNSUPPORTED_MEDIA_TYPE      (HttpStatus.UNSUPPORTED_MEDIA_TYPE,"Unsupported media type"),
    ACCESS_DENIED               (HttpStatus.FORBIDDEN,             "Access denied"),

    // ── Invoice Domain ───────────────────────────────────────────
    INV_INVOICE_NOT_FOUND          (HttpStatus.NOT_FOUND,              "Invoice not found"),
    INV_INVOICE_ALREADY_EXISTS     (HttpStatus.CONFLICT,               "Invoice number already exists"),
    INV_INVALID_STATUS_TRANSITION  (HttpStatus.UNPROCESSABLE_ENTITY,   "Invalid invoice status transition"),
    INV_INVOICE_ALREADY_PAID       (HttpStatus.UNPROCESSABLE_ENTITY,   "Invoice is already fully paid"),
    INV_INVOICE_CANCELLED          (HttpStatus.UNPROCESSABLE_ENTITY,   "Invoice has been cancelled"),
    INV_PAYMENT_EXCEEDS_DUE        (HttpStatus.UNPROCESSABLE_ENTITY,   "Payment amount exceeds due amount"),
    INV_DUPLICATE_PAYMENT          (HttpStatus.CONFLICT,               "Payment reference already recorded"),

    // ── Customer Domain ──────────────────────────────────────────
    INV_CUSTOMER_NOT_FOUND         (HttpStatus.NOT_FOUND,              "Customer not found"),
    INV_CUSTOMER_ALREADY_EXISTS    (HttpStatus.CONFLICT,               "Customer already exists"),

    // ── Merchant Domain ──────────────────────────────────────────
    INV_MERCHANT_NOT_FOUND         (HttpStatus.NOT_FOUND,              "Merchant not found"),
    INV_MERCHANT_ALREADY_EXISTS    (HttpStatus.CONFLICT,               "Merchant already exists");

    private final HttpStatus httpStatus;
    private final String defaultMessage;

    ErrorCode(HttpStatus httpStatus, String defaultMessage) {
        this.httpStatus = httpStatus;
        this.defaultMessage = defaultMessage;
    }

    public HttpStatus getHttpStatus() { return httpStatus; }
    public String getDefaultMessage() { return defaultMessage; }
}