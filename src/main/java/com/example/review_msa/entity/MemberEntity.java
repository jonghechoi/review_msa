package com.example.review_msa.entity;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Table(name="MEMBER")
@NoArgsConstructor
public class MemberEntity {
    @Id
    String mid;
    String memberid;
    String mname;
    String mpass;
    String memail;
    String kemail;
    String tel;
    String mphone;
    String mcreatedate;
    String mmodifyDate;
    String roleid;
    String deleteyn;
    String beforempass;

    @Builder
    @QueryProjection
    public MemberEntity(String mid, String memberid, String mname, String mpass, String memail, String kemail, String tel,
                        String mphone, String mcreatedate, String mmodifyDate, String roleid, String deleteyn, String beforempass) {
        this.mid = mid;
        this.memberid = memberid;
        this.mname = mname;
        this.mpass = mpass;
        this.memail = memail;
        this.kemail = kemail;
        this.tel = tel;
        this.mphone = mphone;
        this.mcreatedate = mcreatedate;
        this.mmodifyDate = mmodifyDate;
        this.roleid = roleid;
        this.deleteyn = deleteyn;
        this.beforempass = beforempass;
    }
}
