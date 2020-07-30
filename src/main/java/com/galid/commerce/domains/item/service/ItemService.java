package com.galid.commerce.domains.item.service;

import com.galid.commerce.domains.item.domain.ItemEntity;
import com.galid.commerce.domains.item.domain.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public Long saveItem(ItemEntity item) {
        return itemRepository.save(item).getItemId();
    }

    public List<ItemEntity> findAllItem() {
        return itemRepository.findAll();
    }

    public ItemEntity findItem(Long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));
    }
}

