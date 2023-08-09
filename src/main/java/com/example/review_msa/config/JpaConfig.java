package com.example.review_msa.config;

import com.example.review_msa.factory.CustomJPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.review_msa.entity.QMemberEntity;
import com.example.review_msa.entity.QReviewEntity;

import javax.persistence.EntityManager;

@Configuration
public class JpaConfig {
    @Bean
    public CustomJPAQueryFactory customJPAQueryFactory(EntityManager entityManager) {
        return new CustomJPAQueryFactory(entityManager);
    }

    @Bean
    public QReviewEntity qReviewEntity() {
        return QReviewEntity.reviewEntity;
    }

    @Bean
    public QMemberEntity qMemberEntity() {
        return QMemberEntity.memberEntity;
    }
}
