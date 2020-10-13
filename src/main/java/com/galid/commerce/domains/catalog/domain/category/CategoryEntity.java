package com.galid.commerce.domains.catalog.domain.category;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "categories")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;
    private String categoryName;
    private Long parentId;

    public CategoryEntity(String categoryName, Long parentId) {
        this.categoryName = categoryName;
        this.parentId = parentId;
    }
}
