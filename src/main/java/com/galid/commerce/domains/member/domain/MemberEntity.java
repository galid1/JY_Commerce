package com.galid.commerce.domains.member.domain;

import com.galid.commerce.common.config.logging.BaseEntity;
import com.galid.commerce.common.value.Address;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "members")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberEntity extends BaseEntity {
    @Id @GeneratedValue
    private Long memberId;

    private String authId;
    private String authPw;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Embedded
    private Address address;


    @Builder
    private MemberEntity(String authId, String authPw, Address address) {
        this.authId = authId;
        this.authPw = authPw;
        this.role = Role.USER;
        this.address = address;
    }

}
