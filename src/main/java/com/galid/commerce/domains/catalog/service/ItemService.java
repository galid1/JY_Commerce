package com.galid.commerce.domains.catalog.service;

import com.galid.commerce.domains.catalog.domain.ItemEntity;
import com.galid.commerce.domains.catalog.domain.ItemRepository;
import com.galid.commerce.domains.catalog.infra.ItemFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemFactory itemFactory;

    @Transactional
    public Long saveItem(AddItemRequest request) {
        ItemEntity newItem = itemFactory.createItem(request);
        return itemRepository.save(newItem).getItemId();
    }

    public List<ItemEntity> findAllItem() {
        return itemRepository.findAll();
    }

    public ItemEntity findItem(Long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));
    }
}

