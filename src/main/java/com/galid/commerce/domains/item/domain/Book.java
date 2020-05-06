package com.galid.commerce.domains.item.domain;

import javax.persistence.Entity;

@Entity
public class Book extends ItemEntity{
    private String author;
    private String isbn;
}
