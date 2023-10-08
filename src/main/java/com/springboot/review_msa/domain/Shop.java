package com.springboot.review_msa.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import reactor.util.annotation.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name="SHOP")
@NoArgsConstructor
@AllArgsConstructor
public class Shop {
    @Id
    private String sid;
    @OneToMany(mappedBy = "sid", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();
    private String roleid;
    private String screatedate;
    private String sdeleteyn;
    private String aconfirmyn;
    private String aconfirmfinalyn;
    private String sconfirmyn;
    private String spass;
    private String sname;
    private String sphone;
    private String sloc;
    private String slocx;
    private String slocy;
    private String slocshort;
    private String sintro;
    private String smodifydate;
    private String sclosingdate;
    private String swebsite;
    private String sfoodstyle;
    private String smphoto;
    private String sopeninghour;
    private String sclosinghour;
//    private String sopeninghourString;
//    private String sdepositString;
//    private String smealfeeString;
//    private String lunchString;
//    private String dinnerString;
//    private int rno;
    private int sdeposit;
    private Integer smealfee;
    private int lunch;
    private int dinner;
    private int adgrade;
}
