package com.galid.commerce.domains.item.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@DiscriminatorValue("BOOK")
@Getter
@Setter
public class Book extends ItemEntity{
    private String author;
    private String isbn;
}
