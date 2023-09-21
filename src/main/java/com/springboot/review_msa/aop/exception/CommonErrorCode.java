package com.springboot.review_msa.aop.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode {
    INVALID_INPUT_VALUE(400, "", "Invalid Input Value"),
    METHOD_NOT_ALLOWED(405, "", " Invalid Input Value"),
    HANDLE_ACCESS_DENIED(403, "", "Access is Denied"),
    DATA_NOT_INSERTED(407, "", "Data Not Inserted"),
    DATA_NOT_UPDATED(407, "", "Data Not Updated");

    private final int status;
    private final String code;
    private final String message;
}
