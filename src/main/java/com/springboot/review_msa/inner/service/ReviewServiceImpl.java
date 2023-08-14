package com.springboot.review_msa.inner.service;

import com.springboot.review_msa.outer.dto.ReviewDTO;
import com.springboot.review_msa.inner.repository.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    ReviewMapper reviewRepository;

    public List<ReviewDTO> reviewIndex() {
        return reviewRepository.getReivewListIndex();
    }

}
