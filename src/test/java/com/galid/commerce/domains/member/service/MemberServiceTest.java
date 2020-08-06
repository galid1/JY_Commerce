package com.galid.commerce.domains.member.service;

import com.galid.commerce.common.BaseTest;
import com.galid.commerce.common.value.Address;
import com.galid.commerce.domains.member.domain.MemberEntity;
import com.galid.commerce.domains.member.domain.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest extends BaseTest {
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PasswordEncoder encoder;

    @Test
    public void 회원가입() throws Exception {
        //given
        SignUpRequest request = new SignUpRequest("TEST", "TEST", "TEST", "TEST");

        //when
        Long userId = memberService.signUp(request);
        MemberEntity findMember = memberRepository.findById(userId).get();

        //then
        assertEquals(findMember.getAuthId(), request.getAuthId());
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        //given
        SignUpRequest request1 = new SignUpRequest("TEST", "TEST", "TEST", "TEST");
        SignUpRequest request2 = new SignUpRequest("TEST", "TEST", "TEST", "TEST");

        //when
        memberService.signUp(request1);

        //then
        assertThrows(IllegalStateException.class, () -> memberService.signUp(request2));
    }

}