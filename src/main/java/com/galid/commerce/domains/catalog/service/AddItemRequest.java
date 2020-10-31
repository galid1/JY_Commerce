package com.galid.commerce.domains.catalog.service;

import com.galid.commerce.domains.catalog.infra.ItemType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AddItemRequest {
    private ItemType itemType = ItemType.BOOK;

    // BOOK
    private String bookName;
    private String bookImagePath;
    private int bookPrice;
    private int bookStockQuantity;
    private String bookAuthor;
    private String bookIsbn;

    // MOVIE
    private String movieDirector;
    private String movieActor;

    // ALBUM
    private String albumArtist;
    private String albumSong;
}
