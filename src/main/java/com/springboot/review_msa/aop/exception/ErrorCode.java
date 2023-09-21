package com.springboot.review_msa.aop.exception;

public interface ErrorCode {
    int getStatus();
    String getCode();
    String getMessage();
}
