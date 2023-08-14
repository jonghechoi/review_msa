package com.springboot.review_msa.outer.web.rest;

import com.springboot.review_msa.outer.dto.ReviewDTO;
import com.springboot.review_msa.inner.service.ReviewServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ReviewResource {
    private ReviewServiceImpl reviewService;

    /**
     *	Index review
     */
    //@CrossOrigin(origins = "*")
    @GetMapping("index_review")
    public List<ReviewDTO> index_review() {
        List<ReviewDTO> list = reviewService.reviewIndex();
        list.stream()
            .forEach(review -> System.out.println("content --> " + review.getReviewcontent()));
        return reviewService.reviewIndex();
    }
}
