package com.springboot.review_msa.domain;

import com.querydsl.core.annotations.QueryProjection;
import com.springboot.review_msa.aop.exception.ReviewErrorCode;
import com.springboot.review_msa.aop.exception.ReviewTreatmentException;
import com.springboot.review_msa.repository.MemberRepositoryInterface;
import com.springboot.review_msa.repository.ReservationRepositoryInterface;
import com.springboot.review_msa.repository.ShopRepositoryInterface;
import com.springboot.review_msa.web.dto.ReviewDTO;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Table(name="REVIEW")
@NoArgsConstructor
//@SequenceGenerator(
//        name="SEQU_REVIEW_RID_GEN",
//        sequenceName = "SEQU_REVIEW_RID",
//        allocationSize = 1
//)
@Data
public class Review {
    @Id
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "SEQU_REVIEW_RID_GEN"
//            )
    private String reviewid;
    @ManyToOne
    @JoinColumn(name="sid")
    private Shop sid;
    @ManyToOne
    @JoinColumn(name="mid")
    private Member mid;
    @ManyToOne
    @JoinColumn(name="rid")
    private Reservation rid;

    private String reviewcontent;
    private String reviewcreatedate;
    private String reviewmodifydate;
    private String reviewphoto;
    private String reviewsphoto;
    private String reviewmain;
    private double reviewstar;

    @Builder
    @QueryProjection
    public Review(String reviewid, Shop sid, Member mid, Reservation rid, String reviewcontent, String reviewcreatedate,
                  String reviewmodifydate,  String reviewphoto, String reviewsphoto, String reviewmain, double reviewstar) {
        this.reviewid = reviewid;
        this.sid = sid;
        this.mid = mid;
        this.rid = rid;
        this.reviewcontent = reviewcontent;
        this.reviewcreatedate = reviewcreatedate;
        this.reviewmodifydate = reviewmodifydate;
        this.reviewphoto = reviewphoto;
        this.reviewsphoto = reviewsphoto;
        this.reviewmain = reviewmain;
        this.reviewstar = reviewstar;
    }

    public Review createReview(ReviewDTO r, EntityManager em, ShopRepositoryInterface shopRepositoryInterface,
                               MemberRepositoryInterface memberRepositoryInterface, ReservationRepositoryInterface reservationRepositoryInterface)
    throws RuntimeException{
        Query query = em.createNativeQuery("SELECT SEQU_REVIEW_RID.NEXTVAL FROM DUAL");
        Long nextVal = ((BigDecimal) query.getSingleResult()).longValue();

        Shop shop = shopRepositoryInterface.findBySid(r.getSid());
        Member member = memberRepositoryInterface.findByMid(r.getMid());
        Reservation reservation = reservationRepositoryInterface.findByRid(r.getRid());

//        if(shop!=null && member!=null && reservation!=null) {
            Review review = this;
            review.setReviewid("REVIEW_" + String.format("%04d", nextVal));
            review.setReviewcontent(r.getReviewcontent());
            review.setSid(shop);
            review.setMid(member);
            review.setRid(reservation);
            review.setReviewphoto(r.getReviewphoto());
            review.setReviewsphoto(r.getReviewsphoto());
            review.setReviewstar(r.getReviewstar());
            review.setReviewcreatedate(LocalDate.now().toString());

            review.getSid().getReviews().add(review);
            review.getMid().getReviews().add(review);
            review.getRid().getReviews().add(review);

            em.persist(review);
            System.out.println("#".repeat(100));
            System.out.println("review --> " + review);
            System.out.println("#".repeat(100));
            return review;
//        }else {
//            throw new ReviewTreatmentException(ReviewErrorCode.REVIEW_NOT_CREATED);
//        }
    }

    public static Review updateReview(Review r, ReviewDTO reviewDTO,EntityManager em) {
        r.setReviewcontent(reviewDTO.getReviewcontent());
        r.setReviewstar(reviewDTO.getReviewstar());
        r.setReviewphoto(reviewDTO.getReviewphoto());
        r.setReviewsphoto(reviewDTO.getReviewsphoto());
        r.setReviewmodifydate(LocalDate.now().toString());
        em.persist(r);
        return r;
    }
}
