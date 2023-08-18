package com.springboot.review_msa.service;

import com.springboot.review_msa.web.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {
    public List<ReviewDTO> reviewIndex();
}
