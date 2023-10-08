package com.springboot.review_msa.domain;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name="MEMBER")
@NoArgsConstructor
public class Member {
    @Id
    private String mid;
    @OneToMany(mappedBy = "mid", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();
    private String memberid;
    private String mname;
    private String mpass;
    private String memail;
    private String kemail;
    private String tel;
    private String mphone;
    private String mcreatedate;
    private String mmodifydate;
    private String roleid;
    private String deleteyn;
    private String beforempass;

    @Builder
    @QueryProjection
    public Member(String mid, String memberid, String mname, String mpass, String memail, String kemail, String tel,
                  String mphone, String mcreatedate, String mmodifydate, String roleid, String deleteyn, String beforempass) {
        this.mid = mid;
        this.memberid = memberid;
        this.mname = mname;
        this.mpass = mpass;
        this.memail = memail;
        this.kemail = kemail;
        this.tel = tel;
        this.mphone = mphone;
        this.mcreatedate = mcreatedate;
        this.mmodifydate = mmodifydate;
        this.roleid = roleid;
        this.deleteyn = deleteyn;
        this.beforempass = beforempass;
    }
}
