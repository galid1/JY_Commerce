package com.galid.commerce.domains.catalog.query.application;

import com.galid.commerce.domains.catalog.query.dao.CatalogDao;
import com.galid.commerce.domains.catalog.query.dto.CatalogSummary;
import com.galid.commerce.domains.catalog.service.ItemSearchForm;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CatalogServiceTest {
    @InjectMocks
    private CatalogService catalogService;

    @Mock
    private CatalogDao catalogDao;
    
    @Test
    public void 카탈로그_조회() throws Exception {
        //given
        given(catalogDao.searchItem(any(ItemSearchForm.class)))
                .willReturn(createCatalog());

        ItemSearchForm searchForm = new ItemSearchForm();

        //when
        catalogService.getCatalog(searchForm);

        //then
        verify(catalogDao, atLeastOnce())
                .searchItem(any(ItemSearchForm.class));
    }

    private List<CatalogSummary> createCatalog() {
        return List.of(
                new CatalogSummary(1l, "", "", 1000, 0.0, 0)
        );
    }

}