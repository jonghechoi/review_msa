package com.springboot.review_msa.outer.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ReviewDTO {
	private String reviewid, reviewcontent, reviewcreatedate, reviewmodifydate, sid, mid, rid, reviewphoto, reviewsphoto, sname, mname;
	private double tasteStar, moodStar, serviceStar;

	private int rno, reviewstar;

	private MultipartFile reviewfile1;
}
