package com.galid.commerce.domains.item.service;

import com.galid.commerce.common.BaseTest;
import com.galid.commerce.domains.item.domain.ItemEntity;
import com.galid.commerce.domains.item.domain.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class ItemServiceTest extends BaseTest {
   @Autowired
   private ItemService itemService;
   @Autowired
   private ItemRepository itemRepository;

   @Test
   public void 아이템_저장() {
       //given
      ItemEntity newItem = ItemEntity.builder()
              .name("TEST")
              .price(1)
              .stockQuantity(1)
              .build();

       //when
      Long newItemId = itemService.saveItem(newItem);

      //then
      ItemEntity findItem = itemRepository.findById(newItemId).get();
      assertEquals(findItem.getName(), newItem.getName());
      assertEquals(findItem.getPrice(), newItem.getPrice());
      assertEquals(findItem.getStockQuantity(), newItem.getStockQuantity());
   }
}
