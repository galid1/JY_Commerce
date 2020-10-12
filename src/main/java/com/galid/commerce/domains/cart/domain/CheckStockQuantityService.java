package com.galid.commerce.domains.cart.domain;

import com.galid.commerce.domains.catalog.domain.item.ItemEntity;
import com.galid.commerce.domains.catalog.domain.item.ItemRepository;
import com.galid.commerce.domains.catalog.service.NotEnoughStockQuantityException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckStockQuantityService {
    private final ItemRepository itemRepository;

    public void checkEnoughStockQuantity(int orderCount, Long itemId) {
        ItemEntity itemEntity = itemRepository.findById(itemId).get();

        if(itemEntity.getStockQuantity() < orderCount)
            throw new NotEnoughStockQuantityException();
    }
}
