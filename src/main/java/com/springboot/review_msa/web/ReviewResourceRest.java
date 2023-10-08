package com.springboot.review_msa.web;

import com.springboot.review_msa.aop.exception.BusinessException;
import com.springboot.review_msa.aop.exception.CommonErrorCode;
import com.springboot.review_msa.domain.Review;
import com.springboot.review_msa.service.impl.FileServiceImpl;
import com.springboot.review_msa.web.dto.ReviewDTO;
import com.springboot.review_msa.service.impl.ReviewServiceImpl;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/review")
public class ReviewResourceRest {
    private ReviewServiceImpl reviewService;
    private FileServiceImpl fileService;
    /**
     *	indexReview
     */
    @CrossOrigin(origins = "*")
    @GetMapping("index")
    public List<ReviewDTO> indexReview() {
        return reviewService.indexReview();
    }

    @PostMapping("write_review")
    public String writeReviewProc(ReviewDTO reviewDto, RedirectAttributes redirectAttributes) throws Exception{
        if(reviewDto.getReviewphoto()!=null) {
            reviewDto = (ReviewDTO)fileService.fileCheck(reviewDto);
        }

        Optional<Review> result = reviewService.writeReview(reviewDto);
//        int reviewYN = reviewService.getUpdateReviewYN(reviewDto.getRid());

        if (result!=null && result.isPresent()) {
//            if(reviewYN == 1) {
                fileService.fileSave(reviewDto);
                return "1";
//            }
        }else {
            throw new BusinessException(CommonErrorCode.DATA_NOT_INSERTED);
        }
    }

    /**
     * CI/CD Test
     */
    @GetMapping("test")
    public String test() {
        return "test success last5";
    }
}
