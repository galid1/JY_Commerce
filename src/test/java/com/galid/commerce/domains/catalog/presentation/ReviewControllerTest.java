package com.galid.commerce.domains.catalog.presentation;

import com.galid.commerce.domains.catalog.service.ItemDetails;
import com.galid.commerce.domains.catalog.service.ItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = ReviewController.class)
@WithMockUser("spring")
class ReviewControllerTest {
    @MockBean
    private ItemService itemService;
    @Autowired
    private MockMvc mvc;

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
}