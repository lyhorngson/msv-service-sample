package com.msv.service_sample.exception;

import com.msv.service_sample.constant.ErrorCode;

public class DuplicateResourceException extends BusinessException {

    public DuplicateResourceException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}