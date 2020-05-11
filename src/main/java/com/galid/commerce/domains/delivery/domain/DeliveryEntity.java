package com.galid.commerce.domains.delivery.domain;

import com.galid.commerce.common.config.logging.BaseEntity;
import com.galid.commerce.common.value.Address;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "delivery")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeliveryEntity extends BaseEntity {
    @Id @GeneratedValue
    private Long id;
    private Address address;

    @Enumerated(value = EnumType.STRING)
    private DeliveryStatus status;
}
