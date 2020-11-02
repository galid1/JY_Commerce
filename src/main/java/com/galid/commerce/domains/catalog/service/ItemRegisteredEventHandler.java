package com.galid.commerce.domains.catalog.service;

import com.galid.commerce.domains.catalog.domain.item.ItemRegisteredEvent;
import com.galid.commerce.domains.catalog.domain.review.ReviewProductEntity;
import com.galid.commerce.domains.catalog.domain.review.ReviewProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemRegisteredEventHandler {
    private final ReviewProductRepository reviewProductRepository;

    @Async
    @EventListener
    public void handleEvent(ItemRegisteredEvent event) {
        reviewProductRepository.save(new ReviewProductEntity(event.getItemId()));
    }
}
