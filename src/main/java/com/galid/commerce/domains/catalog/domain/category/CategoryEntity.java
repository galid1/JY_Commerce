package com.galid.commerce.domains.catalog.domain.category;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;
    private String categoryName;

    @ElementCollection
    @CollectionTable(
            name = "sub_categories",
            joinColumns = @JoinColumn(name = "parent_id")
    )
    @Column(name = "sub_category_id")
    private List<Long> subCategories = new ArrayList<>();

    public CategoryEntity(String categoryName) {
        this.categoryName = categoryName;
    }
}
