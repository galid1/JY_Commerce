package com.galid.commerce.domains.item.domain;

import com.galid.commerce.common.config.logging.BaseEntity;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
@Getter
public class CategoryEntity extends BaseEntity {
    @Id @GeneratedValue
    private Long id;
    private String categoryName;
}
