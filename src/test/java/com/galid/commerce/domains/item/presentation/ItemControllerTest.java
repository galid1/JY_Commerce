package com.galid.commerce.domains.item.presentation;

import com.galid.commerce.domains.item.domain.Book;
import com.galid.commerce.domains.item.domain.ItemEntity;
import com.galid.commerce.domains.item.domain.ItemQuery;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = ItemController.class)
@WithMockUser(value = "spring")
class ItemControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private ItemService itemService;
    @MockBean
    private ItemQuery itemQuery;

    private Long itemId = 1l;

    @BeforeEach
    public void init() {
        given(itemService.saveItem(any()))
                .willReturn(itemId);

        ItemEntity itemEntity = createItem();
        given(itemService.findItem(itemId))
                .willReturn(itemEntity);
    }

    private ItemEntity createItem() {
        return Book.builder()
                .imagePath("test")
                .name("test")
                .price(1000)
                .stockQuantity(1)
                .isbn("test")
                .author("test")
                .build();
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
                .param("imagePath", "TEST")
                .param("price", "1000")
                .param("stockQuantity", "1")
                .param("author", "TEST")
                .param("isbn", "TEST")
        )
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/items/" + itemId));
    }

    @Test
    public void 아이템_리스트_페이지() throws Exception {
        mvc.perform(get("/items"))
                .andExpect(status().isOk())
                .andExpect(view().name("items/itemList"));
    }

    @Test
    public void 아이템_상세_페이지() throws Exception {
        //given
        ItemEntity itemInModel = itemService.findItem(itemId);

        //when, then
        mvc.perform(get("/items/{itemId}", itemId))
                .andExpect(status().isOk())
                .andExpect(model().attribute("item", itemInModel))
                .andExpect(view().name("items/itemDetails"));
    }
}