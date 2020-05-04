package com.galid.commerce.domains.delivery.domain;

import javax.persistence.Embeddable;

@Embeddable
public enum DeliveryStatus {
    SHIPPING, PAYMENT_WAITING
}
