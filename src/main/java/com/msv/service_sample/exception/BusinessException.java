package com.msv.service_sample.exception;

import com.msv.service_sample.constant.ErrorCode;
import lombok.Getter;

/**
 * Base exception for all business/domain rule violations.
 * Always carries an ErrorCode so the global handler maps it correctly.
 */
@Getter
public class BusinessException extends RuntimeException {

    private final ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getDefaultMessage());
        this.errorCode = errorCode;
    }

    public BusinessException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public BusinessException(ErrorCode errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }
}