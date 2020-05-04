package com.galid.commerce.domains.order.domain;

import javax.persistence.Embeddable;

@Embeddable
public enum OrderStatus {
    ORDER_STATUS, CANCEL_STATUS
}
