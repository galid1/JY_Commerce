package com.galid.commerce.domains.catalog.service;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AddItemRequest {
    private String name;
    private String imagePath;
    private int price;
    private int stockQuantity;
    private Long categoryId;
}
