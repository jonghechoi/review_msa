package com.springboot.review_msa.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Table(name="SHOP")
@NoArgsConstructor
public class Shop {
    @Id
    String sid;
    String roleid;
    String screatedate;
    String sdeleteyn;
    String aconfirmyn;
    String aconfirmfinalyn;
    String confirmyn;
    String spass;
    String sname;
    String sphone;
    String sloc;
    String slocx;
    String slocy;
    String slocshort;
    String sintro;
    String smodifydate;
    String sclosingdate;
    String swebsite;
    String sfoodstyle;
    String smphoto;
    String sopeninghour;
    String sclosinghour;
    String sopeninghourString;
    String sdepositString;
    String smealfeeString;
    String lunchString;
    String dinnerString;
    int rno;
    int sdeposit;
    int smealfee;
    int lunch;
    int dinner;
    int adgrade;

    @Builder
    public Shop(String sid, String roleid, String screatedate, String sdeleteyn, String aconfirmyn, String aconfirmfinalyn,
                String confirmyn, String spass, String sname, String sphone, String sloc, String slocx, String slocy,
                String slocshort, String sintro, String smodifydate, String sclosingdate, String swebsite, String sfoodstyle,
                String smphoto, String sopeninghour, String sclosinghour, String sopeninghourString, String sdepositString,
                String smealfeeString, String lunchString, String dinnerString, int rno, int sdeposit, int smealfee, int lunch, int dinner, int adgrade) {
        this.sid = sid;
        this.roleid = roleid;
        this.screatedate = screatedate;
        this.sdeleteyn = sdeleteyn;
        this.aconfirmyn = aconfirmyn;
        this.aconfirmfinalyn = aconfirmfinalyn;
        this.confirmyn = confirmyn;
        this.spass = spass;
        this.sname = sname;
        this.sphone = sphone;
        this.sloc = sloc;
        this.slocx = slocx;
        this.slocy = slocy;
        this.slocshort = slocshort;
        this.sintro = sintro;
        this. smodifydate = smodifydate;
        this.sclosingdate = sclosingdate;
        this.swebsite = swebsite;
        this.sfoodstyle = sfoodstyle;
        this.smphoto = smphoto;
        this.sopeninghour = sopeninghour;
        this.sclosinghour = sclosinghour;
        this.sopeninghourString = sopeninghourString;
        this.sdepositString = sdepositString;
        this.smealfeeString = smealfeeString;
        this.lunchString = lunchString;
        this.dinnerString = dinnerString;
        this.rno = rno;
        this.sdeposit = sdeposit;
        this.smealfee = smealfee;
        this.lunch = lunch;
        this.dinner = dinner;
        this.adgrade = adgrade;
    }
}
