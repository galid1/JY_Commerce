package com.galid.commerce.domains.catalog.query.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CategoryDto {
    private Long categoryId;
    private String categoryName;
    private Long parentId;
    private List<CategoryDto> subCategories;

    @QueryProjection
    public CategoryDto(Long categoryId, String categoryName, Long parentId) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.parentId = parentId;
    }
}
