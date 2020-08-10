package com.galid.commerce.domains.item.presentation;

import com.galid.commerce.domains.item.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import javax.validation.constraints.NotNull;

import java.util.Map;

import static java.util.Map.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(value = ItemController.class)
@WithMockUser(value = "spring")
class ItemControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private ItemService itemService;

    private Long itemId = 1l;

    @BeforeEach
    public void init() {
        given(itemService.saveItem(any()))
                .willReturn(itemId);
    }

    @Test
    public void 아이템_생성_페이지() throws Exception {
        mvc.perform(get("/items/new"))
                .andExpect(view().name("items/createItemForm"))
                .andExpect(status().isOk());
    }

    @Test
    public void 아이템_생성() throws Exception {
        mvc.perform(post("/items/new")
                .param("name", "TEST")
                .param("price", "1000")
                .param("stockQuantity", "1")
                .param("author", "TEST")
                .param("isbn", "TEST")
        )
            .andExpect(view().name("redirect:/items/details?item_id=" + itemId))
            .andExpect(status().is3xxRedirection());
    }

}