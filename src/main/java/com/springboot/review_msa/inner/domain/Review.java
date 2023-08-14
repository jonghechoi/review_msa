package com.springboot.review_msa.inner.domain;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Table(name="REVIEW")
@NoArgsConstructor
public class Review {
    @Id
    String reviewid;
    String reviewcontent;
    String reviewcreatedate;
    String reviewmodifydate;
    String sid;
    String mid;
    String rid;
    String reviewphoto;
    String reviewsphoto;
    String reviewmain;
    int reviewstar;

    @Builder
    @QueryProjection
    public Review(String reviewid, String reviewcontent, String reviewcreatedate, String reviewmodifydate, String sid,
                  String mid, String rid, String reviewphoto, String reviewsphoto, String reviewmain, int reviewstar) {
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
}
