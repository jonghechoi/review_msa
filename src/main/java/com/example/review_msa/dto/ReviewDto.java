package com.example.review_msa.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ReviewDto {
	private String reviewid, reviewcontent, reviewcreatedate, reviewmodifydate, sid, mid, rid, reviewphoto, reviewsphoto, sname, mname;
	private double tasteStar, moodStar, serviceStar;

	private int rno, reviewstar;

	private MultipartFile reviewfile1;
}
