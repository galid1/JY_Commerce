package com.galid.commerce.domains.item.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "category")
@Getter
public class CategoryEntity {
    @Id @GeneratedValue
    private Long id;
    private String categoryName;
}
