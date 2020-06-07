package com.galid.commerce.domains.member.service;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class SignUpRequest {
    private String id;
    private String password;
    private String city;
    private String street;
}
