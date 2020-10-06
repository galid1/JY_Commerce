package com.galid.commerce.domains.order.query.dto;

import com.galid.commerce.common.value.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MyOrderDetailsReceiverInfoDto {
    private String receiverName;
    private String contact;
    private Address address;
}
