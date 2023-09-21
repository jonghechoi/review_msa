package com.springboot.review_msa.aop.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ReviewErrorCode implements ErrorCode {
    REVIEW_NOT_CREATED(501, "", "Review Not Created"),
    REVIEW_NOT_UPDATED(502, "", "Review Not Updated"),
    REVIEW_NOT_DELETED(503, "", "Review Not Deleted");

    private final int status;
    private final String code;
    private final String message;
}
