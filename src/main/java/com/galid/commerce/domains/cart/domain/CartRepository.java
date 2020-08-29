package com.galid.commerce.domains.cart.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<CartEntity, Long> {
    CartEntity findFirstByMemberId(Long memberId);
}
