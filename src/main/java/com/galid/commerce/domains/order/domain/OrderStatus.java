package com.galid.commerce.domains.order.domain;

import javax.persistence.Embeddable;

@Embeddable
public enum OrderStatus {
    ORDERED_STATUS, CANCEL_STATUS
}
