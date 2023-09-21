package com.springboot.review_msa.aop.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ErrorResponseDTO {
    private final int status;
    private final String message;
}
