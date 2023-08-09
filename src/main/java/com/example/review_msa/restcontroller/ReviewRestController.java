package com.example.review_msa.restcontroller;

import com.example.review_msa.dto.ReviewDto;
import com.example.review_msa.service.ReviewServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ReviewRestController {
    private ReviewServiceImpl reviewService;

    /**
     *	Index review
     */
    @GetMapping("index_review")
    public List<ReviewDto> index_review() {
        List<ReviewDto> list = reviewService.reviewIndex();
        list.stream().forEach(review -> System.out.println("content --> " + review.getReviewcontent()));
        return reviewService.reviewIndex();
    }
}
