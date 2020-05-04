package com.galid.commerce.domains.delivery.domain;

import com.galid.commerce.common.config.logging.BaseEntity;
import com.galid.commerce.common.value.Address;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "delivery")
@Getter
public class DeliveryEntity extends BaseEntity {
    @Id @GeneratedValue
    private Long id;
    private Address address;

    @Enumerated(value = EnumType.STRING)
    private DeliveryStatus status;
}
