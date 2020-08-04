package com.galid.commerce.domains.item.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    public Book(String name, int price, int stockQuantity, String author, String isbn) {
        super.setName(name);
        super.setPrice(price);
        super.setStockQuantity(stockQuantity);
        this.author = author;
        this.isbn = isbn;
    }
}
