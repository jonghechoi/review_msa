package com.example.review_msa.factory;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManager;

public class CustomJPAQueryFactory extends JPAQueryFactory {

    public CustomJPAQueryFactory(EntityManager entityManager) { super(entityManager); }
}
