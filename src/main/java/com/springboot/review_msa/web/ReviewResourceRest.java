package com.springboot.review_msa.web;

import com.springboot.review_msa.web.dto.ReviewDTO;
import com.springboot.review_msa.service.impl.ReviewServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RestController
@AllArgsConstructor
public class ReviewResourceRest {
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

    /**
     *  write_review
     */
    @GetMapping("write_review/{rid}")
    public String write_review(@PathVariable String rid, Model model) {
        ReviewDTO reviewDto = reviewService.getReviewSelect(rid);
        model.addAttribute("reviewVo", reviewDto);
        return "/pages/mydining/write_review";
    }

    /**
     * admin_review
     * @return
     */
}
