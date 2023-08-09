package com.example.review_msa.repository;

import com.example.review_msa.dto.ReviewDto;
import com.example.review_msa.entity.QMemberEntity;
import com.example.review_msa.entity.QReviewEntity;
import com.example.review_msa.factory.CustomJPAQueryFactory;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewRepository {
    private final CustomJPAQueryFactory jpaQueryFactory;
    private final QReviewEntity qReviewEntity;
    private final QMemberEntity qMemberEntity;

    public List<ReviewDto> getReivewListIndex() {
        return jpaQueryFactory
                .select(
                        Projections.fields(ReviewDto.class,
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
