package com.galid.commerce.domains.item.presentation;

import com.galid.commerce.domains.item.domain.Book;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BookForm {
    private String name;
    private int price;
    private int stockQuantity;
    private String author;
    private String isbn;

    public Book toBookEntity() {
        Book book = Book.builder()
                .author("TEST")
                .isbn("TEST")
                .name("TEST")
                .price(1000)
                .stockQuantity(2)
                .build();

        return book;
    }
}
