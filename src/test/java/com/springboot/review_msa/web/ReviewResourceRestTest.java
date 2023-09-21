package com.springboot.review_msa.web;

import com.springboot.review_msa.service.impl.ReviewServiceImpl;
import com.springboot.review_msa.web.dto.ReviewDTO;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReviewResourceRest.class)
//@AutoConfigureWebMvc
class ReviewResourceRestTest {
    @Autowired
    private MockMvc mockMvc;

    // ReviewResourceRest 컨트롤러에서 잡고 있는 ReviewServiceImpl 빈 객체에 대해 Mock 형태의 객체를 생성
    @MockBean
    ReviewServiceImpl reviewService;

    @Test
    @DisplayName("인덱스 리뷰 테스트")
    void indexReview() throws Exception {
        // given : Mock 객체가 특정 상황에서 해야하는 행위를 정의하는 메서드
        // willReturn : 반환되어야 하는 값. 즉 "어떤 값이 넘어와야해~"하고 답을 미리 정의. 이러면 실제로 DB에서 값을 가져오지 않을 수 있음.
        given(reviewService.indexReview()).willReturn(List.of(
            new ReviewDTO("R_0001", "정말 맛있어요우~~", "23/08/28", "23/08/28",
                    "S_0001", "M_0050", "", "photo.jpg", "9ed65a25-8931-4df9-bf57-10362de6ac77_photo.jpg",
                    "", "", 2.4, 0, 0, 0, 1234, 0, null
            ))
        );

        // andExpect : 기대하는 값이 나왔는지 체크해 볼 수 있는 메서드
        // perform : REST API를 테스트할 수 있는 환경을 만들어 줌.
        mockMvc.perform(
                get("/index/")) // post 방식에선 post 메서드를 사용하고 DTO를 Gson 이용해서 json 형식으로 변환 후에 .content 메서드에 인자로 전달하면 됨. 또한 .contentType 메서드의 인자로 "MediaType.APPLICATION_JSON"도 전달해야 함.
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].reviewid").exists()) // json의 depth가 깊어지면 .를 추가하여 탐색 가능
                .andExpectAll()
                .andDo(print());

        // verify : 해당 객체의 메서드가 실행되었는지 체크
        verify(reviewService).indexReview();
    }




}