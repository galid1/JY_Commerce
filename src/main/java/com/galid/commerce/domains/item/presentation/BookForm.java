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
        Book book = new Book();
        book.setName(this.name);
        book.setPrice(this.price);
        book.setStockQuantity(this.stockQuantity);
        book.setAuthor(this.author);
        book.setIsbn(this.isbn);

        return book;
    }
}
