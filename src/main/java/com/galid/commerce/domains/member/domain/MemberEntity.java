package com.galid.commerce.domains.member.domain;

import com.galid.commerce.common.value.Address;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "member")
@Getter
public class MemberEntity {
    private Long id;
    private String name;
    private Address address;
}
