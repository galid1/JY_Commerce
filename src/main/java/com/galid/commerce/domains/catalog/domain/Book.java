package com.galid.commerce.domains.catalog.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@DiscriminatorValue("BOOK")
@Getter
public class Book extends ItemEntity{
    private String author;
    private String isbn;

    @Builder
    public Book(String name, String imagePath, int price, int stockQuantity, String author, String isbn) {
        super.setName(name);
        super.setImagePath(imagePath);
        super.setPrice(price);
        super.setStockQuantity(stockQuantity);
        this.author = author;
        this.isbn = isbn;
    }
}
