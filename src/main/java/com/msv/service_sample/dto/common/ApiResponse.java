package com.msv.service_sample.dto.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

/**
 * Standard API success response envelope.
 *
 * All successful API responses follow this structure (RFC 7807-inspired):
 *
 * {
 *   "success": true,
 *   "status":  200,
 *   "message": "Invoice retrieved successfully",
 *   "data":    { ... },
 *   "meta":    { ... },         // optional — pagination, counts
 *   "timestamp": "2024-02-01T10:00:00Z",
 *   "path":    "/api/v1/invoices/abc-123",
 *   "requestId": "f47ac10b-58cc..."
 * }
 */
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Standard API response envelope")
public class ApiResponse<T> {

    @Schema(description = "Whether the request was successful", example = "true")
    private final boolean success;

    @Schema(description = "HTTP status code", example = "200")
    private final int status;

    @Schema(description = "Human-readable message", example = "Invoice retrieved successfully")
    private final String message;

    @Schema(description = "Response payload")
    private final T data;

    @Schema(description = "Pagination or extra metadata")
    private final MetaData meta;

    @Schema(description = "ISO 8601 UTC timestamp", example = "2024-02-01T10:00:00Z")
    @Builder.Default
    private final Instant timestamp = Instant.now();

    @Schema(description = "Request path", example = "/api/v1/invoices")
    private final String path;

    @Schema(description = "Unique request trace ID", example = "f47ac10b-58cc-4372-a567-0e02b2c3d479")
    private final String requestId;

    // ── Static factory methods ────────────────────────────────────

    public static <T> ApiResponse<T> ok(T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .status(200)
                .message("Success")
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> ok(T data, String message) {
        return ApiResponse.<T>builder()
                .success(true)
                .status(200)
                .message(message)
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> ok(T data, String message, MetaData meta) {
        return ApiResponse.<T>builder()
                .success(true)
                .status(200)
                .message(message)
                .data(data)
                .meta(meta)
                .build();
    }

    public static <T> ApiResponse<T> created(T data, String message) {
        return ApiResponse.<T>builder()
                .success(true)
                .status(201)
                .message(message)
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> noContent() {
        return ApiResponse.<T>builder()
                .success(true)
                .status(204)
                .message("No content")
                .build();
    }

    /**
     * Attach request context (path, requestId) — called in controller layer.
     */
    public ApiResponse<T> withContext(String path, String requestId) {
        return ApiResponse.<T>builder()
                .success(this.success)
                .status(this.status)
                .message(this.message)
                .data(this.data)
                .meta(this.meta)
                .timestamp(this.timestamp)
                .path(path)
                .requestId(requestId)
                .build();
    }
}