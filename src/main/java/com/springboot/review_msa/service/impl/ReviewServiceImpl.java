package com.springboot.review_msa.service.impl;

import com.springboot.review_msa.domain.Review;
import com.springboot.review_msa.repository.ReviewRepositoryInterface;
import com.springboot.review_msa.service.ReviewService;
import com.springboot.review_msa.web.dto.ReviewDTO;
import com.springboot.review_msa.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    ReviewRepositoryInterface reviewRepositoryInterface;
    @Autowired
    EntityManager entityManager;

    @Override
    public List<ReviewDTO> reviewIndex() {
        return reviewRepository.getReivewListIndex();
    }

    // 이 부분 insert 할 때 null 값 exception 처리 필요
    @Transactional
    public Review getWriteReview(ReviewDTO reviewDto) {
        reviewDto.setReviewstar(reviewDto);
        Review review = Review.createReview( reviewDto, entityManager);

        System.out.println(this.getClass().getSimpleName());





        return reviewRepositoryInterface.save(review);
    }

    public int getUpdateReviewYN(String rid) {
        return (int)reviewRepository.getUpdateReviewYN(rid);
    }

    public ReviewDTO getReviewSelect(String rid) {
        return reviewRepository.reviewSelect(rid);
    }
}