package com.springboot.review_msa.aop.exception;

public class BusinessException extends RuntimeException {
    private final ErrorCode objectErrorCode;

    public BusinessException(ErrorCode objectErrorCode) {
        super(objectErrorCode.getMessage());
        this.objectErrorCode = objectErrorCode;
    }

    public ErrorCode getErrorCode() {
        return objectErrorCode;
    }
}
