package com.springboot.review_msa.web;

import com.springboot.review_msa.service.impl.FileServiceImpl;
import com.springboot.review_msa.service.impl.ReviewServiceImpl;
import com.springboot.review_msa.web.dto.ReviewDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
public class ReviewResource {
    private FileServiceImpl fileService;
    private ReviewServiceImpl reviewService;

    /**
     * write_review_proc
     */
    @PostMapping("write_review")
    public String write_review_proc(ReviewDTO reviewDto, RedirectAttributes redirectAttributes) throws Exception{

        //boolean fileFlag = fileService.fileCheck(reviewDto);

        // 굳이 여기서 insert를 할 필요가 있나??
        reviewService.getWriteReview(reviewDto);
//        int reviewYN = reviewService.getUpdateReviewYN(reviewDto.getRid());
//
//        if(fileFlag) {
//
//        }


//        if (result == 1 && reviewYN == 1) {
//            fileService.fileSave(reviewDto);
//            redirectAttributes.addFlashAttribute("reviewWrite", "ok");
//            return "redirect:/mydining_visited";
//        }

        //return "redirect:/mydining_visited";
        return "hi~";
    }

    /**
     * admin_review
     * @return
     */
    @GetMapping("admin_review")
    public String admin_review() {
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
