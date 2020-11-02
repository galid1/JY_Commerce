package com.galid.commerce.domains.catalog.domain.review;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewProductRepository extends JpaRepository<ReviewProductEntity, Long> {
    ReviewProductEntity findByProductId(Long productId);
}
