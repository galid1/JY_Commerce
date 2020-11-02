package com.galid.commerce.domains.catalog.service;

import com.galid.commerce.domains.catalog.domain.item.ItemEntity;
import com.galid.commerce.domains.catalog.domain.item.ItemRegisteredEvent;
import com.galid.commerce.domains.catalog.domain.item.ItemRepository;
import com.galid.commerce.domains.catalog.domain.review.ReviewProductEntity;
import com.galid.commerce.domains.catalog.domain.review.ReviewProductRepository;
import com.galid.commerce.domains.catalog.infra.ItemFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    private final ApplicationContext applicationContext;
    private final ItemRepository itemRepository;
    private final ItemFactory itemFactory;

    @Transactional
    public Long saveItem(AddItemRequest request) {
        ItemEntity newItem = itemFactory.createItem(request);
        ItemEntity savedItem = itemRepository.save(newItem);

        // 아이템 생성됨 이벤트 발행
        applicationContext.publishEvent(new ItemRegisteredEvent(savedItem.getItemId()));

        return savedItem.getItemId();
    }

    public ItemDetails findItem(Long itemId) {
        ItemEntity itemEntity = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));

        return new ItemDetails(itemEntity);
    }
}

