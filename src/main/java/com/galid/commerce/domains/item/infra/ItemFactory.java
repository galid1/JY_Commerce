package com.galid.commerce.domains.item.infra;

import com.galid.commerce.domains.item.domain.ItemEntity;
import com.galid.commerce.domains.item.service.AddItemRequest;

public interface ItemFactory {
    ItemEntity createItem(AddItemRequest request);
}
