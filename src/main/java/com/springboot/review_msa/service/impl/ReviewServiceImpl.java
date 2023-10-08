package com.springboot.review_msa.service.impl;

import com.springboot.review_msa.aop.exception.BusinessException;
import com.springboot.review_msa.aop.exception.CommonErrorCode;
import com.springboot.review_msa.aop.exception.ReviewErrorCode;
import com.springboot.review_msa.aop.exception.ReviewTreatmentException;
import com.springboot.review_msa.domain.Review;
import com.springboot.review_msa.repository.*;
import com.springboot.review_msa.web.dto.ReviewDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ReviewServiceImpl {
    private ReviewRepository reviewRepository;
    private ReviewRepositoryInterface reviewRepositoryInterface;
    private ShopRepositoryInterface shopRepositoryInterface;
    private MemberRepositoryInterface memberRepositoryInterface;
    private ReservationRepositoryInterface reservationRepositoryInterface;
    private EntityManager entityManager;
    private ReviewMainLogic reviewMainLogic;
    private List<ReviewDTO> reviewList;
    private final Object lock = new Object();

    public ReviewServiceImpl(ReviewRepository reviewRepository, ReviewRepositoryInterface reviewRepositoryInterface,
                             ShopRepositoryInterface shopRepositoryInterface, MemberRepositoryInterface memberRepositoryInterface,
                             ReservationRepositoryInterface reservationRepositoryInterface,
                             EntityManager entityManager, ReviewMainLogic reviewMainLogic) {
        this.reviewRepository = reviewRepository;
        this.reviewRepositoryInterface = reviewRepositoryInterface;
        this.shopRepositoryInterface = shopRepositoryInterface;
        this.memberRepositoryInterface = memberRepositoryInterface;
        this.reservationRepositoryInterface = reservationRepositoryInterface;
        this.entityManager = entityManager;
        this.reviewMainLogic = reviewMainLogic;
    }

    /**
     * index 페이지에 노출될 4개의 리뷰 리턴
     */
    public List<ReviewDTO> indexReview() {
        synchronized (lock) {
            reviewList = reviewMainLogic.indexReview();
            // 이 부분에 메인노출로직 들어가야함(진행 필요)
            return reviewList;
        }
    }

    @Transactional
    public Optional<Review> writeReview(ReviewDTO reviewDTO) throws ReviewTreatmentException {
        reviewDTO.processReviewstar(reviewDTO);
        Review review = new Review();
        Review reviewEntity = review.createReview(reviewDTO, entityManager, shopRepositoryInterface, memberRepositoryInterface, reservationRepositoryInterface);
        Optional<Review> optionalReview = Optional.of(reviewRepositoryInterface.save(reviewEntity));

        if(optionalReview.isPresent()) {
            reviewMainLogic.newReviewStore(optionalReview);
            return optionalReview;
        }else {
            throw new ReviewTreatmentException(ReviewErrorCode.REVIEW_NOT_CREATED);
        }
    }

    public Optional<Review> updateReview(ReviewDTO reviewDTO, String rid) {
        // DB 데이터 update
        Review reviewEntity = reviewRepositoryInterface.findByRid(rid);
        Review reviewUpdated = Review.updateReview(reviewEntity, reviewDTO, entityManager);
        if(Optional.of(reviewRepositoryInterface.save(reviewUpdated)).isPresent()) {
            // DB에 제대로 update 되었으면 index에 노출될 리뷰 리스트 수정
            synchronized (lock) {
                reviewList.stream()
                        .filter(review -> review.getRid().equals(rid))
                        .map(review -> {
                            review.setReviewcontent(reviewDTO.getReviewcontent());
                            review.processReviewstar(reviewDTO);
                            review.setReviewphoto(reviewDTO.getReviewphoto());
                            review.setReviewsphoto(reviewDTO.getReviewsphoto());
                            review.setReviewmodifydate(LocalDate.now().toString());
                            return review;
                        });
            }
            reviewRepository.updateReviewYN(rid);
        }else {
            throw new BusinessException(CommonErrorCode.DATA_NOT_UPDATED);
        }
        return Optional.of(reviewRepositoryInterface.save(reviewUpdated));
    }

//    public int getUpdateReviewYN(String rid) {
//        return (int)reviewRepository.updateReviewYN(rid);
//    }

    public ReviewDTO selectReview(String rid) {
        return reviewRepository.selectReview(rid);
    }
}