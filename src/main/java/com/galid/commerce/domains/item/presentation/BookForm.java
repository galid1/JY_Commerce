package com.galid.commerce.domains.item.presentation;

import com.galid.commerce.domains.item.domain.Book;
import com.galid.commerce.domains.item.domain.ItemEntity;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BookForm {
    @NotNull
    private String name;
    @NotNull
    private String imagePath;
    @NotNull
    private int price;
    @NotNull
    private int stockQuantity;
    private String author;
    private String isbn;

    public ItemEntity toEntity() {
        return Book.builder()
                .author(author)
                .isbn(isbn)
                .imagePath(imagePath)
                .name(name)
                .price(price)
                .stockQuantity(stockQuantity)
                .build();
    }
}
