package com.galid.commerce.domains.catalog.service;

import com.galid.commerce.domains.catalog.domain.category.CategoryEntity;
import com.galid.commerce.domains.catalog.domain.category.CategoryRepository;
import com.galid.commerce.domains.catalog.query.dto.CategoryDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {
    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Test
    public void 최상위_카테고리_생성() throws Exception {
        //given
        List<CategoryEntity> categoryEntities = createCategoryEntities();
        given(categoryRepository.findAll())
                .willReturn(categoryEntities);

        //when
        CategoryDto categoryRoot = categoryService.createCategoryRoot();

        //then
        verify(categoryRepository, atLeastOnce()).findAll();
        // root
        assertThat(categoryRoot.getSubCategories().size(), is(2));
        // sub1
        assertThat(categoryRoot.getSubCategories().get(0).getSubCategories().size(), is(2));
        // sub2
        assertThat(categoryRoot.getSubCategories().get(1).getSubCategories().size(), is(2));
    }

    private List<CategoryEntity> createCategoryEntities() {
        CategoryEntity sub1 = new CategoryEntity("SUB1", 0l);
        CategoryEntity sub2 = new CategoryEntity("SUB2", 0l);
        CategoryEntity sub11 = new CategoryEntity("SUB1-1", 1l);
        CategoryEntity sub12 = new CategoryEntity("SUB1-2", 1l);
        CategoryEntity sub21 = new CategoryEntity("SUB2-1", 2l);
        CategoryEntity sub22 = new CategoryEntity("SUB2-2", 2l);
        ReflectionTestUtils.setField(sub1, "categoryId", 1l);
        ReflectionTestUtils.setField(sub2, "categoryId", 2l);
        ReflectionTestUtils.setField(sub11, "categoryId", 3l);
        ReflectionTestUtils.setField(sub12, "categoryId", 4l);
        ReflectionTestUtils.setField(sub21, "categoryId", 5l);
        ReflectionTestUtils.setField(sub22, "categoryId", 6l);

        List<CategoryEntity> categoryEntities = List.of(
                sub1, sub2, sub11, sub12, sub21, sub22
        );
        return categoryEntities;
    }

}