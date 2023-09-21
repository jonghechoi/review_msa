package com.springboot.review_msa.service.impl;

import com.springboot.review_msa.web.dto.ReviewDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service("fileService")
@Slf4j
public class FileServiceImpl {
	/**
	 * FileSave
	 */
	public void fileSave(ReviewDTO reviewDto) throws Exception {
		String projectPath = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\upload\\";

		if(reviewDto.getReviewfile1().getOriginalFilename() != null && !reviewDto.getReviewfile1().getOriginalFilename().equals("")) {
			File saveFile = new File(projectPath + reviewDto.getReviewsphoto());
			reviewDto.getReviewfile1().transferTo(saveFile);
		}
	}

	/**
	 * FileCheck
	 */
	public Object fileCheck(ReviewDTO reviewDto) throws Exception {
		if(reviewDto.getReviewfile1().getOriginalFilename() != null
				&& !reviewDto.getReviewfile1().getOriginalFilename().equals("")) {

			UUID uuid = UUID.randomUUID();
			String reviewphoto = reviewDto.getReviewfile1().getOriginalFilename();
			String reviewsphoto = uuid +"_"+ reviewphoto;

			reviewDto.setReviewphoto(reviewphoto);
			reviewDto.setReviewsphoto(reviewsphoto);
		}else {
			System.out.println("No File Upload");
		}
		return reviewDto;
	}
}
