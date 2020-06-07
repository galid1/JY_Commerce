package com.galid.commerce.domains.member.service;

import com.galid.commerce.common.value.Address;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class SignUpRequest {
    private String id;
    private String password;
    private Address address;
}
