package com.galid.commerce.infra;

import com.galid.commerce.domains.member.domain.MemberEntity;
import com.galid.commerce.domains.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationConverter {
    private final MemberRepository memberRepository;

    public MemberEntity getMemberFromAuthentication(Authentication authentication) {
        String authId = authentication.getName();

        return memberRepository.findFirstByAuthId(authId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디입니다."));
    }
}
