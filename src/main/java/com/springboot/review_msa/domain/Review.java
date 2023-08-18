package com.springboot.review_msa.domain;

import com.querydsl.core.annotations.QueryProjection;
import com.springboot.review_msa.web.dto.ReviewDTO;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Table(name="REVIEW")
@NoArgsConstructor
@SequenceGenerator(
        name="SEQU_REVIEW_RID_GEN",
        sequenceName = "SEQU_REVIEW_RID",
        allocationSize = 1
)
@Data
public class Review {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQU_REVIEW_RID_GEN"
            )
    private Long id;
    private String reviewid;
    private String reviewcontent;
    private String reviewcreatedate;
    private String reviewmodifydate;
    private String sid;
    private String mid;
    private String rid;
    private String reviewphoto;
    private String reviewsphoto;
    private String reviewmain;
    private double reviewstar;

    @Builder
    @QueryProjection
    public Review(Long id, String reviewid, String reviewcontent, String reviewcreatedate, String reviewmodifydate, String sid,
                  String mid, String rid, String reviewphoto, String reviewsphoto, String reviewmain, double reviewstar) {
        this.id = id;
        this.reviewid = reviewid;
        this.reviewcontent = reviewcontent;
        this.reviewcreatedate = reviewcreatedate;
        this.reviewmodifydate = reviewmodifydate;
        this.sid = sid;
        this.mid = mid;
        this.rid = rid;
        this.reviewphoto = reviewphoto;
        this.reviewsphoto = reviewsphoto;
        this.reviewmain = reviewmain;
        this.reviewstar = reviewstar;
    }

    public static Review createReview(ReviewDTO reviewDTO, EntityManager entityManager) {
        Query query = entityManager.createNativeQuery("SELECT SEQU_REVIEW_RID.NEXTVAL FROM DUAL");
        Long nextVal = ((BigDecimal) query.getSingleResult()).longValue();

        Review review = new Review();
        review.setReviewid("REVIEW_" + String.format("%04d", nextVal));
        review.setReviewcontent(reviewDTO.getReviewcontent());
        review.setSid(reviewDTO.getSid());
        review.setMid(reviewDTO.getMid());
        review.setRid(reviewDTO.getRid());
        review.setReviewphoto(reviewDTO.getReviewphoto());
        review.setReviewsphoto(reviewDTO.getReviewsphoto());
        review.setReviewstar(reviewDTO.getReviewstar());
        review.setReviewcreatedate(LocalDate.now().toString());

        entityManager.persist(review);
        return review;
    }
}
