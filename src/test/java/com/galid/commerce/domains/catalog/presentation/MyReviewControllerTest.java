package com.galid.commerce.domains.catalog.presentation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(value = MyReviewController.class)
@WithMockUser("spring")
class MyReviewControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void 내_리뷰목록_페이지_로딩() throws Exception {
        //given, when
        ResultActions resultActions = mvc.perform(get("/my/reviews"));

        // then
        resultActions
                .andExpect(view().name("reviews/myReviewList"))
                .andExpect(status().isOk());
    }
}