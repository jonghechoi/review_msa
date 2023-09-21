package com.springboot.review_msa.service.namePlease;

import com.springboot.review_msa.repository.ReviewRepository;
import com.springboot.review_msa.repository.ShopRepository;

import com.springboot.review_msa.web.dto.ReviewDTO;
import com.springboot.review_msa.web.dto.ShopDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.*;

//@SpringBootTest(classes = ReviewAggregate.class);
@SpringBootTest
class ReviewMainLogicTest {
    @MockBean
    ReviewRepository reviewRepository;
    @MockBean
    ShopRepository shopRepository;

    List<ShopDTO> shopList;
    List<ReviewDTO> reviewList;

    private Map<String, ReviewDTO> reviewDTOMap;

    @Test
    @BeforeEach
    @DisplayName("Shop, Review 데이터 매핑")
    void findSameSid() {
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
        shopList = shopRepository.getShopAdvertisementGrade();
        reviewList = reviewRepository.getReivewListIndex();

        Map<String, ShopDTO> shopMap = shopList.stream()
                .collect(Collectors.toMap(ShopDTO::getSid, shop -> shop));
        Map<String, ReviewDTO> reviewMap = reviewList.stream()
                .collect(Collectors.toMap(ReviewDTO::getSid, review -> review));

        shopMap.forEach((key, value) -> {
            reviewMap.get(key).setAdgrade(value.getAdgrade());
        });

        // then
        assertThat(reviewMap.get("S_0001").getAdgrade()).isEqualTo(5);

        reviewDTOMap = reviewMap;
    }
}