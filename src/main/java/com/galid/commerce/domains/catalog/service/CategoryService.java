package com.galid.commerce.domains.catalog.service;

import com.galid.commerce.domains.catalog.domain.category.CategoryRepository;
import com.galid.commerce.domains.catalog.query.dto.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Cacheable("categories")
    public CategoryDto createCategoryRoot() {
        Map<Long, List<CategoryDto>> groupingByParent = categoryRepository.findAll()
                .stream()
                .map(ce -> new CategoryDto(ce.getCategoryId(), ce.getCategoryName(), ce.getParentId()))
                .collect(groupingBy(cd -> cd.getParentId()));

        CategoryDto rootCategoryDto = new CategoryDto(0l, "ROOT", null);
        addSubCategories(rootCategoryDto, groupingByParent);

        return rootCategoryDto;
    }

    private void addSubCategories(CategoryDto parent, Map<Long, List<CategoryDto>> groupingByParentId) {
        // 1. parent의 키로 subCategories를 찾는다.
        List<CategoryDto> subCategories = groupingByParentId.get(parent.getCategoryId());

        // 종료 조건
        if (subCategories == null)
            return;

        // 2. sub categories 셋팅
        parent.setSubCategories(subCategories);

        // 3. 재귀적으로 subcategories들에 대해서도 수행
        subCategories.stream()
                .forEach(s -> {
                    addSubCategories(s, groupingByParentId);
                });
    }
}
