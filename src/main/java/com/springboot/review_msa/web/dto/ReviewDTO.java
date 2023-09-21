package com.springboot.review_msa.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor // repository에서 CustomJPAQueryFactory 이용해 쿼리 실행할 때 기본생성자가 반드시 필요함. 없으면 => NoSuchMethodException
public class ReviewDTO {
	private String reviewid, reviewcontent, reviewcreatedate, reviewmodifydate, sid, mid, rid, reviewphoto, reviewsphoto, sname, mname;
	private double reviewstar, tasteStar, moodStar, serviceStar;

	private int rno, adgrade;

	private MultipartFile reviewfile1;

	public void processReviewstar(ReviewDTO reviewDto) {
		this.reviewstar = Math.round((reviewDto.getTasteStar() + reviewDto.getMoodStar() + reviewDto.getServiceStar() / 3.0 * 10)) / 10.0;
	}
}
