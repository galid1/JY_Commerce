package com.galid.commerce.domains.item.service;

import com.galid.commerce.domains.item.presentation.Sorter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemSearchForm {
    private String name;
    private Sorter sorter;
}
