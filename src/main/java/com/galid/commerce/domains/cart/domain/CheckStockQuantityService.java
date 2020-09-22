package com.galid.commerce.domains.cart.domain;

import com.galid.commerce.domains.item.domain.ItemEntity;
import com.galid.commerce.domains.item.domain.ItemRepository;
import com.galid.commerce.domains.item.domain.NotEnoughStockQuantityException;
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
