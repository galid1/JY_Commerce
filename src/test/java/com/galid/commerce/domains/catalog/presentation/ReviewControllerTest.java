package com.galid.commerce.domains.catalog.presentation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.galid.commerce.common.value.Address;
import com.galid.commerce.domains.catalog.service.ItemDetails;
import com.galid.commerce.domains.catalog.service.ItemService;
import com.galid.commerce.domains.catalog.service.ReviewRequest;
import com.galid.commerce.domains.catalog.service.ReviewService;
import com.galid.commerce.domains.member.domain.MemberEntity;
import com.galid.commerce.infra.AuthenticationConverter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = ReviewController.class)
@WithMockUser("spring")
class ReviewControllerTest {
    @MockBean
    private ItemService itemService;
    @MockBean
    private ReviewService reviewService;
    @MockBean
    private AuthenticationConverter authenticationConverter;
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objMapper;

    @Test
    public void 리뷰_작성_페이지() throws Exception {
        //given
        ItemDetails details = new ItemDetails();
        given(itemService.findItem(anyLong()))
                .willReturn(details);

        //when
        ResultActions resultActions = mvc.perform(get("/reviews/review")
                .param("productId", "1"));

        //then
        resultActions
                .andExpect(model().attributeExists("product"))
                .andExpect(view().name("reviews/review"))
                .andExpect(status().isOk());
    }

    @Test
    public void 리뷰_작성_요청() throws Exception {
        //given
        Long returnValue = 1l;

        given(authenticationConverter.getMemberFromAuthentication(any(Authentication.class)))
                .willReturn(createMember());
        given(reviewService.review(anyLong(), any(ReviewRequest.class)))
                .willReturn(returnValue);

        //when
        ResultActions resultActions = mvc.perform(post("/reviews/review")
                .content(createReviewRequestBody())
                .contentType(MediaType.APPLICATION_JSON_VALUE));

        //then
        MvcResult result = resultActions
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(result.getResponse().getContentAsString(), returnValue.toString());
    }

    private MemberEntity createMember() {
        MemberEntity member = MemberEntity.builder().build();
        ReflectionTestUtils.setField(member, "memberId", 1l);
        return member;
    }

    private String createReviewRequestBody() throws JsonProcessingException {
        ReviewRequest request = new ReviewRequest();
        request.setProductId(1l);
        request.setProductName("TEST");
        request.setRating(1);
        request.setReview("TEST");
        return objMapper.writeValueAsString(request);
    }
}