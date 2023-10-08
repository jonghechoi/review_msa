package com.springboot.review_msa.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.springboot.review_msa.domain.Review;
import com.springboot.review_msa.repository.redis.ReviewRedisRepository;
import com.springboot.review_msa.repository.ReviewRepository;
import com.springboot.review_msa.repository.ShopRepository;
import com.springboot.review_msa.web.dto.ReviewDTO;
import com.springboot.review_msa.web.dto.ShopDTO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class ReviewMainLogic {
    private final RedisTemplate<String, ReviewDTO> redisTemplate;
    private final ReviewRepository reviewRepository;
    private final ReviewRedisRepository<ReviewDTO> reviewRedisRepository;
    private Map<String, Integer> shopMap;
    private Map<String, ReviewDTO> reviewMap;

    /**
     * When application initiated constructor bring whole Review data
     */
    public ReviewMainLogic(RedisTemplate<String, ReviewDTO> redisTemplate, ReviewRedisRepository<ReviewDTO> reviewRedisRepository,
                           ReviewRepository reviewRepository, ShopRepository shopRepository) {
        this.redisTemplate = redisTemplate;
        this.reviewRepository = reviewRepository;
        this.reviewRedisRepository = reviewRedisRepository;
        List<ShopDTO> shopList = shopRepository.getShopAdvertisementGrade();
        List<ReviewDTO> reviewList = reviewRepository.getReivewListIndex();

        this.shopMap = new HashMap<>(shopList.stream()
                .collect(Collectors.toMap(ShopDTO::getSid, shop -> shop.getAdgrade())));
        this.reviewMap = new HashMap<>(reviewList.stream()
                .collect(Collectors.toMap(ReviewDTO::getReviewid, review -> review)));
        reviewToRedis();
        reviewFromRedis();
    }

    private void reviewToRedis() {
        List<ReviewDTO> reviewList = reviewRepository.getReivewListIndex();

        reviewList.stream()
                .forEach(review -> {
                    ValueOperations<String, ReviewDTO> redis = redisTemplate.opsForValue();
                    redis.set(review.getReviewid(), review);
                });
    }

    private void reviewFromRedis() {
        reviewMap = reviewRedisRepository.getReviewKeyFromRedis(new ArrayList<>(reviewMap.keySet()));
        shopMap.forEach((shopKey, shop) -> {
            reviewMap.forEach((reviewKey, review) -> {
                if(shopKey.equals(review.getSid())) {
                    review.setAdgrade(shop);
                };
            });
        });
    }

    public void newReviewStore(Optional<Review> review) {
        HashSet<ReviewDTO> set = new HashSet<>();
    }

    public List<ReviewDTO> indexReview() {
        return new ArrayList<>(reviewMap.values());
    }
}
