package com.galid.commerce.domains.catalog.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
}
