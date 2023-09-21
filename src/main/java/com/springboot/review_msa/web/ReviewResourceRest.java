package com.springboot.review_msa.web;

import com.springboot.review_msa.web.dto.ReviewDTO;
import com.springboot.review_msa.service.impl.ReviewServiceImpl;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/review")
public class ReviewResourceRest {
    private ReviewServiceImpl reviewService;

    /**
     *	indexReview
     */
    @CrossOrigin(origins = "*")
    @GetMapping("index")
    public List<ReviewDTO> indexReview() {
        return reviewService.indexReview();
    }

    /**
     * CI/CD Test
     */
    @GetMapping("test")
    public String test() {
        return "test success last5";
    }
}
