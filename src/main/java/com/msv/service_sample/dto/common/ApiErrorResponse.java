package com.msv.service_sample.dto.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.msv.service_sample.constant.ErrorCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.List;

/**
 * Standard API error response envelope — inspired by RFC 7807 (Problem Details).
 *
 * All error responses follow this structure:
 *
 * {
 *   "success":   false,
 *   "status":    404,
 *   "code":      "INV_INVOICE_NOT_FOUND",
 *   "message":   "Invoice not found",
 *   "errors":    [ { "field": "dueDate", "message": "must be in the future" } ],
 *   "timestamp": "2024-02-01T10:00:00Z",
 *   "path":      "/api/v1/invoices/bad-id",
 *   "requestId": "f47ac10b-58cc..."
 * }
 */
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Standard API error response")
public class ApiErrorResponse {

    @Schema(description = "Always false for error responses", example = "false")
    @Builder.Default
    private final boolean success = false;

    @Schema(description = "HTTP status code", example = "404")
    private final int status;

    @Schema(description = "Machine-readable error code", example = "INV_INVOICE_NOT_FOUND")
    private final String code;

    @Schema(description = "Human-readable error message", example = "Invoice not found")
    private final String message;

    @Schema(description = "Field-level validation errors")
    private final List<FieldError> errors;

    @Schema(description = "ISO 8601 UTC timestamp")
    @Builder.Default
    private final Instant timestamp = Instant.now();

    @Schema(description = "Request path", example = "/api/v1/invoices/abc")
    private final String path;

    @Schema(description = "Unique request trace ID")
    private final String requestId;

    // ── Static Factories ─────────────────────────────────────────

    public static ApiErrorResponse of(ErrorCode errorCode, String path, String requestId) {
        return ApiErrorResponse.builder()
                .status(errorCode.getHttpStatus().value())
                .code(errorCode.name())
                .message(errorCode.getDefaultMessage())
                .path(path)
                .requestId(requestId)
                .build();
    }

    public static ApiErrorResponse of(ErrorCode errorCode, String message, String path, String requestId) {
        return ApiErrorResponse.builder()
                .status(errorCode.getHttpStatus().value())
                .code(errorCode.name())
                .message(message)
                .path(path)
                .requestId(requestId)
                .build();
    }

    public static ApiErrorResponse withFieldErrors(ErrorCode errorCode,
                                                   List<FieldError> errors,
                                                   String path,
                                                   String requestId) {
        return ApiErrorResponse.builder()
                .status(errorCode.getHttpStatus().value())
                .code(errorCode.name())
                .message(errorCode.getDefaultMessage())
                .errors(errors)
                .path(path)
                .requestId(requestId)
                .build();
    }

    // ── Field Error ──────────────────────────────────────────────

    @Getter
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Schema(description = "Validation error on a specific field")
    public static class FieldError {

        @Schema(description = "Field name", example = "dueDate")
        private final String field;

        @Schema(description = "Rejected value", example = "2020-01-01")
        private final Object rejectedValue;

        @Schema(description = "Error message", example = "must be a future date")
        private final String message;

        public static FieldError of(String field, Object rejectedValue, String message) {
            return FieldError.builder()
                    .field(field)
                    .rejectedValue(rejectedValue)
                    .message(message)
                    .build();
        }

        public static FieldError of(String field, String message) {
            return FieldError.builder()
                    .field(field)
                    .message(message)
                    .build();
        }
    }
}