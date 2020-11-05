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

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {
    @InjectMocks
    private ItemService itemService;
    @Mock
    private ItemRepository itemRepository;

    @Test
    public void 아이템_추가() {
        //given
        //BOOK
        // 책 생성 요청
        AddItemRequest request = createAddItemRequest();
        ItemEntity item = createItem(request);

        // 엔티티의 가짜 id
        ReflectionTestUtils.setField(item, "itemId", item.getItemId());
        given(itemRepository.findById(any(Long.class)))
                .willReturn(Optional.of(item));
        given(itemRepository.save(any(ItemEntity.class)))
                .willReturn(item);

        //when
        Long newItemId = itemService.saveItem(request);

        //then
        ItemEntity findItem = itemRepository.findById(newItemId).get();
        assertEquals(findItem.getName(), request.getName());
        assertEquals(findItem.getPrice(), request.getPrice());
        assertEquals(findItem.getStockQuantity(), request.getStockQuantity());
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
