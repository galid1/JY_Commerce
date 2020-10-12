package com.galid.commerce.domains.catalog.service;

import com.galid.commerce.domains.catalog.domain.item.Book;
import com.galid.commerce.domains.catalog.domain.item.ItemEntity;
import com.galid.commerce.domains.catalog.domain.item.ItemRepository;
import com.galid.commerce.domains.catalog.infra.ItemFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {
    @InjectMocks
    private ItemService itemService;
    @Mock
    private ItemFactory itemFactory;
    @Mock
    private ItemRepository itemRepository;

    @Test
    public void 아이템_추가() {
        //given
        //BOOK
        // 책 생성 요청
        AddItemRequest bookRequest = createAddBookRequest();
        ItemEntity book = createBook(bookRequest);

        // 책 엔티티의 가짜 id
        Long bookId = 1l;
        ReflectionTestUtils.setField(book, "itemId", bookId);
        given(itemRepository.findById(bookId))
                .willReturn(Optional.ofNullable(book));
        given(itemFactory.createItem(bookRequest))
                .willReturn(book);
        given(itemRepository.save(book))
                .willReturn(book);

        //when
        Long newBookId = itemService.saveItem(bookRequest);

        //then
        ItemEntity findItem = itemRepository.findById(newBookId).get();
        assertEquals(findItem.getName(), bookRequest.getBookName());
        assertEquals(findItem.getPrice(), bookRequest.getBookPrice());
        assertEquals(findItem.getStockQuantity(), bookRequest.getBookStockQuantity());
    }

    private AddItemRequest createAddBookRequest() {
        return AddItemRequest.builder()
                .bookAuthor("TEST")
                .bookImagePath("TEST")
                .bookIsbn("TEST")
                .bookName("TEST")
                .bookPrice(1000)
                .bookStockQuantity(2)
                .build();
    }

    private ItemEntity createBook(AddItemRequest bookRequest) {
        ItemEntity book = Book.builder()
                .price(bookRequest.getBookPrice())
                .name(bookRequest.getBookName())
                .isbn(bookRequest.getBookIsbn())
                .author(bookRequest.getBookAuthor())
                .imagePath(bookRequest.getBookImagePath())
                .stockQuantity(bookRequest.getBookStockQuantity())
                .build();

        return book;
    }
}
