package com.msv.service_sample.exception.handler;

import com.msv.service_sample.constant.ErrorCode;
import com.msv.service_sample.dto.common.ApiErrorResponse;
import com.msv.service_sample.exception.BusinessException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Centralized exception → ApiErrorResponse mapping.
 *
 * Every exception type is mapped here so controllers stay clean.
 * All error responses follow the ApiErrorResponse standard envelope.
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // ── Business / Domain Exceptions ────────────────────────────

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiErrorResponse> handleBusinessException(
            BusinessException ex, HttpServletRequest request) {

        log.warn("Business exception [{}]: {}", ex.getErrorCode().name(), ex.getMessage());

        ApiErrorResponse error = ApiErrorResponse.of(
                ex.getErrorCode(),
                ex.getMessage(),
                request.getRequestURI(),
                getRequestId(request)
        );

        return ResponseEntity
                .status(ex.getErrorCode().getHttpStatus())
                .body(error);
    }

    // ── Validation Exceptions ────────────────────────────────────

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidation(
            MethodArgumentNotValidException ex, HttpServletRequest request) {

        log.warn("Validation failed for request [{}]", request.getRequestURI());

        List<ApiErrorResponse.FieldError> fieldErrors = buildFieldErrors(ex.getBindingResult());

        ApiErrorResponse error = ApiErrorResponse.withFieldErrors(
                ErrorCode.VALIDATION_FAILED,
                fieldErrors,
                request.getRequestURI(),
                getRequestId(request)
        );

        return ResponseEntity
                .status(ErrorCode.VALIDATION_FAILED.getHttpStatus())
                .body(error);
    }

    // ── HTTP-level Exceptions ────────────────────────────────────

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiErrorResponse> handleMethodNotSupported(
            HttpRequestMethodNotSupportedException ex, HttpServletRequest request) {

        ApiErrorResponse error = ApiErrorResponse.of(
                ErrorCode.METHOD_NOT_ALLOWED,
                request.getRequestURI(),
                getRequestId(request)
        );

        return ResponseEntity
                .status(ErrorCode.METHOD_NOT_ALLOWED.getHttpStatus())
                .body(error);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ApiErrorResponse> handleUnsupportedMediaType(
            HttpMediaTypeNotSupportedException ex, HttpServletRequest request) {

        ApiErrorResponse error = ApiErrorResponse.of(
                ErrorCode.UNSUPPORTED_MEDIA_TYPE,
                request.getRequestURI(),
                getRequestId(request)
        );

        return ResponseEntity
                .status(ErrorCode.UNSUPPORTED_MEDIA_TYPE.getHttpStatus())
                .body(error);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorResponse> handleNotReadable(
            HttpMessageNotReadableException ex, HttpServletRequest request) {

        ApiErrorResponse error = ApiErrorResponse.of(
                ErrorCode.INVALID_REQUEST,
                "Request body is missing or malformed",
                request.getRequestURI(),
                getRequestId(request)
        );

        return ResponseEntity
                .status(ErrorCode.INVALID_REQUEST.getHttpStatus())
                .body(error);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiErrorResponse> handleMissingParam(
            MissingServletRequestParameterException ex, HttpServletRequest request) {

        ApiErrorResponse error = ApiErrorResponse.of(
                ErrorCode.INVALID_REQUEST,
                "Required parameter '" + ex.getParameterName() + "' is missing",
                request.getRequestURI(),
                getRequestId(request)
        );

        return ResponseEntity
                .status(ErrorCode.INVALID_REQUEST.getHttpStatus())
                .body(error);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiErrorResponse> handleTypeMismatch(
            MethodArgumentTypeMismatchException ex, HttpServletRequest request) {

        String message = String.format(
                "Parameter '%s' should be of type '%s'",
                ex.getName(),
                ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "unknown"
        );

        ApiErrorResponse error = ApiErrorResponse.of(
                ErrorCode.INVALID_REQUEST,
                message,
                request.getRequestURI(),
                getRequestId(request)
        );

        return ResponseEntity
                .status(ErrorCode.INVALID_REQUEST.getHttpStatus())
                .body(error);
    }

    // ── Catch-all ────────────────────────────────────────────────

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGeneral(
            Exception ex, HttpServletRequest request) {

        log.error("Unexpected error on [{}]: {}", request.getRequestURI(), ex.getMessage(), ex);

        ApiErrorResponse error = ApiErrorResponse.of(
                ErrorCode.INTERNAL_SERVER_ERROR,
                request.getRequestURI(),
                getRequestId(request)
        );

        return ResponseEntity
                .status(ErrorCode.INTERNAL_SERVER_ERROR.getHttpStatus())
                .body(error);
    }

    // ── Private Helpers ──────────────────────────────────────────

    private List<ApiErrorResponse.FieldError> buildFieldErrors(BindingResult result) {
        return result.getFieldErrors().stream()
                .map(fe -> ApiErrorResponse.FieldError.of(
                        fe.getField(),
                        fe.getRejectedValue(),
                        fe.getDefaultMessage()
                ))
                .collect(Collectors.toList());
    }

    private String getRequestId(HttpServletRequest request) {
        String id = request.getHeader("X-Request-ID");
        return (id != null && !id.isBlank()) ? id : UUID.randomUUID().toString();
    }
}