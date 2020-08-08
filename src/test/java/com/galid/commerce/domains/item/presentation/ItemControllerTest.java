package com.galid.commerce.domains.item.presentation;

import com.galid.commerce.common.BaseWebTest;
import com.galid.commerce.domains.item.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ItemControllerTest extends BaseWebTest {
    @MockBean
    private ItemService itemService;

    @BeforeEach
    public void init() {
        given(itemService.saveItem(any()))
                .willReturn(1l);
    }
    @Test
    public void 아이템_생성_페이지() throws Exception {
        //given
        mvc.perform(get("/items/new"))
                .andExpect(status().isOk());
    }

}