package com.springboot.review_msa.repository;

import com.querydsl.sql.SQLExpressions;
import com.springboot.review_msa.domain.QReservation;
import com.springboot.review_msa.domain.QShop;
import com.springboot.review_msa.domain.Review;
import com.springboot.review_msa.web.dto.ReviewDTO;
import com.springboot.review_msa.domain.QMember;
import com.springboot.review_msa.domain.QReview;
import com.springboot.review_msa.config.CustomJPAQueryFactory;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class ReviewRepository {
    private final CustomJPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;
    private final QReview qReview;
    private final QMember qMember;
    private final QReservation qReservation;
    private final QShop qShop;

    public List<ReviewDTO> getReivewListIndex() {
        return jpaQueryFactory
                .select(
                        Projections.fields(ReviewDTO.class,
                            Expressions.template(Integer.class, "function('ROWNUM')").as("rno"),
                            qReview.reviewid,
                            qReview.reviewcontent,
                            qReview.reviewstar,
                            qReview.reviewcreatedate,
                            qReview.reviewmodifydate,
                            qReview.sid,
                            qReview.mid,
                            qReview.rid,
                            qReview.reviewphoto,
                            qReview.reviewsphoto)
                )
                .from(qReview)
                .innerJoin(qMember).on(qReview.mid.eq(qMember.mid))
                .where(qReview.reviewmain.eq("Y"))
                .fetch();
    }

    public long writeReview(ReviewDTO reviewDTO) {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("으아으아으아으아으아으아으아으아으아으아 --> " + Expressions.stringTemplate("to_char({0}, '0000')", Expressions.numberTemplate(Integer.class, "SEQU_REVIEW_RID.nextval")).trim());
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

        String query = "INSERT INTO Review (reviewid, reviewcontent, reviewstar, sid, mid, rid, reviewphoto, reviewsphoto, reviewmain) " +
                "VALUES (:reviewid, :reviewcontent, :reviewstar, :sid, :mid, :rid, :reviewphoto, :reviewsphoto, :reviewmain)";

        entityManager.createNativeQuery(query)
                .setParameter("reviewid", "REVIEW_" + Expressions.stringTemplate("to_char({0}, '0000')", Expressions.numberTemplate(Integer.class, "SEQU_REVIEW_RID.nextval")).trim())
                .setParameter("reviewcontent", "11111111")
                .setParameter("reviewstar", reviewDTO.getReviewstar())
                .setParameter("sid", reviewDTO.getSid())
                .setParameter("mid", reviewDTO.getMid())
                .setParameter("rid", reviewDTO.getRid())
                .setParameter("reviewphoto", reviewDTO.getReviewphoto())
                .setParameter("reviewsphoto", reviewDTO.getReviewsphoto())
                .setParameter("reviewmain", "N")
                .executeUpdate();
        return (long)1.0;
    }

    public long getUpdateReviewYN(String rid) {
        return jpaQueryFactory
                .update(qReservation)
                .set(qReservation.reviewyn, "Y")
                .where(qReservation.reviewyn.eq("N"), qReservation.rid.eq(rid))
                .execute();
    }

    public ReviewDTO reviewSelect(String rid) {
        return jpaQueryFactory
                .select(
                        Projections.bean(
                                ReviewDTO.class,
                                qReservation.rid,
                                qMember.mid,
                                qShop.sid,
                                qShop.sname
                        )
                )
                .from(qReservation)
                .innerJoin(qMember).on(qReservation.mid.eq(qMember.mid))
                .innerJoin(qShop).on(qReservation.sid.eq(qShop.sid))
                .where(qReservation.rid.eq(rid))
                .fetchOne();
    }
}
