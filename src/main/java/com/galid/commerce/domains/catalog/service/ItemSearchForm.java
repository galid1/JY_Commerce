package com.galid.commerce.domains.catalog.service;

import com.galid.commerce.domains.catalog.presentation.Sorter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemSearchForm {
    private String name;
    private Sorter sorter;
    private Long categoryId;
}
