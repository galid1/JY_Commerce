package com.galid.commerce.domains.item.service;

import com.galid.commerce.domains.item.infra.ItemType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AddItemRequest {
    private ItemType itemType;

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
