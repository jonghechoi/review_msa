package com.springboot.review_msa.config.jpa;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.springboot.review_msa.domain.QMember;
import com.springboot.review_msa.domain.QReview;
import com.springboot.review_msa.domain.QReservation;
import com.springboot.review_msa.domain.QShop;

import javax.persistence.EntityManager;

@Configuration
public class JpaConfig {
    @Bean
    public CustomJPAQueryFactory customJPAQueryFactory(EntityManager entityManager) {
        return new CustomJPAQueryFactory(entityManager);
    }

    @Bean
    public EntityManager entityManager(EntityManager entityManager) {
        return entityManager;
    }

    @Bean
    public QReview qReview() {
        return QReview.review;
    }

    @Bean
    public QMember qMember() {
        return QMember.member;
    }

    @Bean
    public QReservation qReservation() { return QReservation.reservation; }

    @Bean
    public QShop qShop() { return QShop.shop; }
}
