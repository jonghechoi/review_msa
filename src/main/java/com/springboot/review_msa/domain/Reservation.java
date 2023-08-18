package com.springboot.review_msa.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="RESERVATION")
@NoArgsConstructor
public class Reservation {
    @Id
    private String rid;
    private String rdate;
    private String rtime;
    private String rmodifydate;
    private String rmodifytime;
    private String rrequest;
    private String rphone;
    private String deleteyn;
    private String sid;
    private String mid;
    private String rtabletype;
    private String rstatus;
    private String reviewyn;
    private int rno;
    private int guestnumber;

    @Builder
    public Reservation(String rid, String rdate, String rtime, String rmodifydate, String rmodifytime,
                       String rrequest, String rphone, String deleteyn, String sid, String mid,
                       String rtabletype, String rstatus, String reviewyn, int rno, int guestnumber) {
        this.rid = rid;
        this.rdate = rdate;
        this.rtime = rtime;
        this.rmodifydate = rmodifydate;
        this.rmodifytime = rmodifytime;
        this.rrequest = rrequest;
        this.rphone = rphone;
        this.deleteyn = deleteyn;
        this.sid = sid;
        this.mid = mid;
        this.rtabletype = rtabletype;
        this.rstatus = rstatus;
        this.reviewyn = reviewyn;
        this.rno = rno;
        this.guestnumber = guestnumber;
    }
}
