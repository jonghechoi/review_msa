package com.springboot.review_msa.web;

import com.springboot.review_msa.aop.exception.BusinessException;
import com.springboot.review_msa.aop.exception.CommonErrorCode;
import com.springboot.review_msa.domain.Review;
import com.springboot.review_msa.service.impl.FileServiceImpl;
import com.springboot.review_msa.service.impl.ReviewServiceImpl;
import com.springboot.review_msa.web.dto.ReviewDTO;
import lombok.AllArgsConstructor;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/api/review")
public class ReviewResource {
    private FileServiceImpl fileService;
    private ReviewServiceImpl reviewService;

    /**
     * writeReview
     */
    @PostMapping("composition")
    public String writeReview(ReviewDTO reviewDto, RedirectAttributes redirectAttributes) throws Exception{
        reviewDto = (ReviewDTO)fileService.fileCheck(reviewDto);

        Optional<Review> result = reviewService.writeReview(reviewDto);
        int reviewYN = reviewService.getUpdateReviewYN(reviewDto.getRid());

        if (result != null && result.isPresent()) {
            if(reviewYN == 1) {
                fileService.fileSave(reviewDto);
                redirectAttributes.addFlashAttribute("reviewWrite", "ok");
                return "redirect:/mydining_visited";
            }
        }else {
            throw new BusinessException(CommonErrorCode.DATA_NOT_INSERTED);
        }
        return "redirect:/mydining_visited";
    }

    @PutMapping("update/{rid}")
    public String updateReview(RedirectAttributes redirectAttributes,
                               ReviewDTO reviewDTO,
                               @PathVariable String rid) {
        Optional<Review> review = reviewService.updateReview(reviewDTO, rid);
        if(review.isPresent()) {
            redirectAttributes.addFlashAttribute("reviewUpdate", "ok");
            return "redirect:/mydining_visited";
        }else {
            throw new BusinessException(CommonErrorCode.DATA_NOT_UPDATED);
        }
    }

    @GetMapping("{rid}")
    public String selectReview(@PathVariable String rid, Model model) {
        ReviewDTO reviewDto = reviewService.selectReview(rid);
        model.addAttribute("reviewVo", reviewDto);
        return "/pages/mydining/write_review";
    }

    /**
     * admin_review
     */
    @GetMapping("admin_review")
    public String adminReview() {
        return "/pages/admin/admin_review";
    }

//    @GetMapping("admin_review_selected")
//    public String admin_review_selected(Model model) {
//        model.addAttribute("review", reviewService.getReviewMainList());
//        return "/pages/admin/admin_review_selected";
//    }
//
//    @GetMapping("admin_review_detail/{goMain}/{rid}")
//    public String admin_review_detail(@PathVariable Boolean goMain,@PathVariable String rid, Model model) {
//        model.addAttribute("reviewJson", reviewService.getReviewDetailSelectJson(rid));
//        model.addAttribute("goMain", goMain);
//        return "/pages/admin/admin_review_detail";
//    }
}
