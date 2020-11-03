package com.galid.commerce.domains.catalog.query.application;

import com.galid.commerce.domains.catalog.query.dao.CatalogDao;
import com.galid.commerce.domains.catalog.service.ItemSearchForm;
import com.galid.commerce.domains.catalog.service.ItemSummaryInItemList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CatalogService {
    private final CatalogDao catalogDao;

    public List<ItemSummaryInItemList> getCatalog(ItemSearchForm searchForm) {
        return catalogDao.searchItem(searchForm);
    }
}
