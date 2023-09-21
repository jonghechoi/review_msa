package com.springboot.review_msa.service.impl;

import com.springboot.review_msa.domain.Review;
import com.springboot.review_msa.repository.ReviewRepository;
import com.springboot.review_msa.repository.ReviewRepositoryInterface;
import com.springboot.review_msa.repository.ShopRepository;
import com.springboot.review_msa.service.namePlease.ReviewMainLogic;
import com.springboot.review_msa.web.dto.ReviewDTO;
import com.springboot.review_msa.web.dto.ShopDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = ReviewServiceImpl.class)
class ReviewServiceImplTest {

    @MockBean
    ReviewRepository reviewRepository;
    @MockBean
    ShopRepository shopRepository;
    @MockBean
    ReviewRepositoryInterface reviewRepositoryInterface;
    @MockBean
    EntityManager entityManager;
    @MockBean
    ReviewServiceImpl reviewService;
    @MockBean
    ReviewMainLogic reviewMainLogic;

    @Test
    @DisplayName("메인 리뷰 노출")
    void indexReview() {
        // given
        when(shopRepository.getShopAdvertisementGrade())
                .thenReturn(List.of(new ShopDTO("S_0001", 5)));
        when(reviewRepository.getReivewListIndex())
                .thenReturn(List.of(new ReviewDTO("REVIEW_0001", "its really good",
                                            "23/09/13", "23/09/13", "S_0001",
                                                     "M_0001", "R_0001", "aaaaaaaaaaaaa.jpeg",
                                               "aaaaaaaaaaaaa.jpeg", "ZERO COMPLEX",
                                                   "고광일", 4.1, 1.0, 2.0, 3.0,
                                                      1, 0, null)));

        // when
        List<ShopDTO> shopList = shopRepository.getShopAdvertisementGrade();
        List<ReviewDTO> reviewList = reviewRepository.getReivewListIndex();


        // then
        assertThat(shopList.get(0).getSid()).isEqualTo("S_0001");
        assertThat(reviewList.get(0).getSid()).isEqualTo("S_0001");
    }

    @Test
    void writeReview() {
        // given
        Review review = new Review(1L, "REVIEW_0001", "its really good",
                "23/09/13", "23/09/13", "S_0001",
                "M_0001", "R_0001", "aaaaaaaaaaaaa.jpeg",
                "aaaaaaaaaaaaa.jpeg", "Y", 3.7);




    }

    @Test
    void getUpdateReviewYN() {
    }

    @Test
    @DisplayName("특정 리뷰 선택")
    void selectReview() {
        // reviewRepository.reviewSelect("R_0001")으로 전달할 경우
        // 해당 메서드의 반환값 == .thenReturn에 들어가는 객체
        // 실제 ReviewRepository의 reviewSelect의 메서드에서 반환되는 값을 이렇게 세팅해놓는 것임.
        when(reviewRepository.selectReview("R_0001"))
            .thenReturn(new ReviewDTO("R_0001", "정말 맛있어요우~~", "23/08/28", "23/08/28",
                        "S_0001", "M_0050", "R_0001", "photo.jpg", "9ed65a25-8931-4df9-bf57-10362de6ac77_photo.jpg",
                        "", "", 2.4, 0, 0, 0, 1234, 0, null)
            );


        // ReviewServiceImpl의 getReviewSelect 메서드에 when에서 세팅된 rid값을 넣으면
        // 실제 getReviewSelect 메서드에서 반환되는 값인 ReviewDto 객체가 나오므로 똑같이 세팅한다.
        ReviewDTO reviewDTO = reviewService.selectReview("R_0001");


        assertEquals(reviewDTO.getRid(), "R_0001");
        // 메서드에 throw를 걸어 놓으면 해당 exception이 발생하는지도 확인이 가능하다.
        // assertThrows(지정한Exception.class, () -> reviewDTO.getRid().equals("R_0002"));
        // 지정한Exception e = assertThrows(지정한Exception.class, () -> reviewDTO.getRid().equals("R_0002"));
        // assertThat(e.getMessage()).isEqualTo("~~~~"));

        verify(reviewRepository).selectReview("R_0001");
    }


}