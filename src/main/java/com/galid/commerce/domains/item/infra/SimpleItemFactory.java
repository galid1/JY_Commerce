package com.galid.commerce.domains.item.infra;

import com.galid.commerce.domains.item.domain.Album;
import com.galid.commerce.domains.item.domain.Book;
import com.galid.commerce.domains.item.domain.ItemEntity;
import com.galid.commerce.domains.item.domain.Movie;
import com.galid.commerce.domains.item.service.AddItemRequest;
import org.springframework.stereotype.Component;

@Component
public class SimpleItemFactory implements ItemFactory{
    @Override
    public ItemEntity createItem(AddItemRequest request) {
        ItemEntity item = null;

        switch (request.getItemType()) {
            case BOOK:
                item = Book.builder()
                        .author(request.getBookAuthor())
                        .imagePath(request.getBookImagePath())
                        .isbn(request.getBookIsbn())
                        .name(request.getBookName())
                        .price(request.getBookPrice())
                        .stockQuantity(request.getBookStockQuantity())
                        .build();
                break;

            case MOVIE:
                item = Movie.builder()
                        .actor(request.getMovieActor())
                        .director(request.getMovieDirector())
                        .build();
                break;

            case ALBUM:
                item = Album.builder()
                        .artist(request.getAlbumArtist())
                        .etc(request.getAlbumSong())
                        .build();
                break;
        }

        return item;
    }
}
