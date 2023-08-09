package com.example.review_msa.service;

import com.example.review_msa.dto.ReviewDto;
import com.example.review_msa.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl {
    @Autowired
    ReviewRepository reviewRepository;

    public List<ReviewDto> reviewIndex() {
        return reviewRepository.getReivewListIndex();
    }

}
