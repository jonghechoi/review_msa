package com.springboot.review_msa.repository;

import com.springboot.review_msa.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepositoryInterface extends JpaRepository<Review, Long> {
    Review findByRid(String rid);
}
