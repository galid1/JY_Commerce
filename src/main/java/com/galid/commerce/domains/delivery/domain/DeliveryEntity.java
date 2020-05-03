package com.galid.commerce.domains.delivery.domain;

import com.galid.commerce.common.config.logging.BaseEntity;
import com.galid.commerce.common.value.Address;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "delivery")
@Getter
public class DeliveryEntity extends BaseEntity {
    private Long id;
    private Address address;
}
