package com.springboot.review_msa.config.jpa;

import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

public class CustomJPAQueryFactory extends JPAQueryFactory {

    public CustomJPAQueryFactory(EntityManager entityManager) { super(entityManager); }
}
