package com.galid.commerce.domains.catalog.infra;

import com.galid.commerce.domains.catalog.domain.item.ItemEntity;
import com.galid.commerce.domains.catalog.service.AddItemRequest;

public interface ItemFactory {
    ItemEntity createItem(AddItemRequest request);
}
