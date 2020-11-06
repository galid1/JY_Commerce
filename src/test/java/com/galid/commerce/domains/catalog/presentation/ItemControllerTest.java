package com.galid.commerce.domains.catalog.presentation;

import com.galid.commerce.domains.catalog.domain.item.ItemEntity;
import com.galid.commerce.domains.catalog.query.dao.CatalogDao;
import com.galid.commerce.domains.catalog.service.AddItemRequest;
import com.galid.commerce.domains.catalog.service.ItemDetails;
import com.galid.commerce.domains.catalog.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = ItemController.class)
@WithMockUser(value = "spring")
class ItemControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private ItemService itemService;

    private Long itemId = 1l;

    private ItemEntity createItem() {
        return ItemEntity.builder()
                .imagePath("test")
                .name("test")
                .price(1000)
                .stockQuantity(1)
                .build();
    }

    @Test
    public void 아이템_생성_페이지() throws Exception {
        //given
        given(itemService.saveItem(any()))
                .willReturn(itemId);

        // when
        ResultActions resultActions = mvc.perform(get("/items/new"));

        // then
        resultActions
                .andExpect(model().attributeExists("form"))
                .andExpect(view().name("items/registerItemForm"))
                .andExpect(status().isOk());
    }

    @Test
    public void 아이템_생성() throws Exception {
        //given
        given(itemService.saveItem(any()))
                .willReturn(itemId);

        //when
        ResultActions resultActions = mvc.perform(post("/items/new")
                .param("name", "TEST")
                .param("imagePath", "TEST")
                .param("price", "1000")
                .param("stockQuantity", "1")
        );

        //then
        resultActions
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/items/" + itemId));
    }

    @Test
    public void 아이템_상세_페이지() throws Exception {
        //given
        given(itemService.findItem(itemId))
                .willReturn(new ItemDetails(createItem()));

        ItemDetails item = itemService.findItem(itemId);

        //when
        ResultActions resultActions = mvc.perform(get("/items/{itemId}", itemId));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(model().attribute("item", item))
                .andExpect(view().name("items/itemDetails"));
    }
}