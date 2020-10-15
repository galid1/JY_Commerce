package com.galid.commerce.common.config.security;

import com.galid.commerce.domains.member.domain.MemberEntity;
import com.galid.commerce.domains.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DatabaseUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberEntity findUser = memberRepository.findFirstByAuthId(username)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 ID 입니다."));

        return new User(findUser.getAuthId(),
                        findUser.getAuthPw(),
                        List.of(new SimpleGrantedAuthority(findUser.getRole().getRole())));
    }
}
