package com.galid.commerce.domains.catalog.service;

import com.galid.commerce.domains.catalog.domain.item.ItemEntity;
import com.galid.commerce.domains.catalog.domain.item.ItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {
    @InjectMocks
    private ItemService itemService;
    @Mock
    private ItemRepository itemRepository;

    @Test
    public void 아이템_추가() {
        // given
        AddItemRequest addItemRequest = createAddItemRequest();
        given(itemRepository.save(any(ItemEntity.class)))
                .willReturn(createItem(addItemRequest));

        // when
        itemService.saveItem(addItemRequest);

        // then
        verify(itemRepository, atLeastOnce()).save(any(ItemEntity.class));
    }

    @Test
    public void 아이템_찾기() throws Exception {
        //given
        Long ITEM_ID = 1l;
        given(itemRepository.findById(any(Long.class)))
                .willReturn(Optional.of(createItem(createAddItemRequest())));

        //when
        itemService.findItem(ITEM_ID);

        //then
        verify(itemRepository, atLeastOnce())
                .findById(any(Long.class));
    }

    private AddItemRequest createAddItemRequest() {
        return AddItemRequest.builder()
                .imagePath("TEST")
                .name("TEST")
                .price(1000)
                .stockQuantity(2)
                .build();
    }

    private ItemEntity createItem(AddItemRequest request) {
        ItemEntity item = ItemEntity.builder()
                .price(request.getPrice())
                .name(request.getName())
                .imagePath(request.getImagePath())
                .stockQuantity(request.getStockQuantity())
                .build();

        ReflectionTestUtils.setField(item, "itemId", 1l);
        return item;
    }
}
