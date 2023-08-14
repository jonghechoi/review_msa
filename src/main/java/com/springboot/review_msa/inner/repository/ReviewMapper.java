package com.springboot.review_msa.inner.repository;

import com.springboot.review_msa.outer.dto.ReviewDTO;
import com.springboot.review_msa.entity.QMemberEntity;
import com.springboot.review_msa.entity.QReviewEntity;
import com.springboot.review_msa.config.CustomJPAQueryFactory;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewMapper {
    private final CustomJPAQueryFactory jpaQueryFactory;
    private final QReviewEntity qReviewEntity;
    private final QMemberEntity qMemberEntity;

    public List<ReviewDTO> getReivewListIndex() {
        return jpaQueryFactory
                .select(
                        Projections.fields(ReviewDTO.class,
                            Expressions.template(Integer.class, "function('ROWNUM')").as("rno"),
                            qReviewEntity.reviewid,
                            qReviewEntity.reviewcontent,
                            qReviewEntity.reviewstar,
                            qReviewEntity.reviewcreatedate,
                            qReviewEntity.reviewmodifydate,
                            qReviewEntity.sid,
                            qReviewEntity.mid,
                            qReviewEntity.rid,
                            qReviewEntity.reviewphoto,
                            qReviewEntity.reviewsphoto)
                )
                .from(qReviewEntity)
                .innerJoin(qMemberEntity).on(qReviewEntity.mid.eq(qMemberEntity.mid))
                .where(qReviewEntity.reviewmain.eq("Y"))
                .fetch();
    }
}
