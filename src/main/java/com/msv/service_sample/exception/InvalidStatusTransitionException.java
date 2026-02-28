package com.msv.service_sample.exception;

import com.msv.service_sample.constant.ErrorCode;

// ── Invalid Status Transition ────────────────────────────────────
public class InvalidStatusTransitionException extends BusinessException {

    public InvalidStatusTransitionException(String from, String to) {
        super(
            ErrorCode.INV_INVALID_STATUS_TRANSITION,
            String.format("Cannot transition invoice from [%s] to [%s]", from, to)
        );
    }
}