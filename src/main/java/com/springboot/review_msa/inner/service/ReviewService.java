package com.springboot.review_msa.inner.service;

import com.springboot.review_msa.outer.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {
    public List<ReviewDTO> reviewIndex();
}
