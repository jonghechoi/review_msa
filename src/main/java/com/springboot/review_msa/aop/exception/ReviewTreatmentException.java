package com.springboot.review_msa.aop.exception;

public class ReviewTreatmentException extends RuntimeException{
    private final ErrorCode objectErrorCode;

    public ReviewTreatmentException(ErrorCode objectErrorCode) {
        super(objectErrorCode.getMessage());
        this.objectErrorCode = objectErrorCode;
    }

    public ErrorCode getErrorCode() { return objectErrorCode; }
}
