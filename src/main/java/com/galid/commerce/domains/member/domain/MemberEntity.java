package com.galid.commerce.domains.member.domain;

import com.galid.commerce.common.config.logging.BaseEntity;
import com.galid.commerce.common.value.Address;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "members")
@Getter
public class MemberEntity extends BaseEntity {
    @Id @GeneratedValue
    private Long id;
    private String name;
    @Embedded
    private Address address;
}
