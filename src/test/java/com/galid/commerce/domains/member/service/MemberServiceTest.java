package com.galid.commerce.domains.member.service;

import com.galid.commerce.common.value.Address;
import com.galid.commerce.domains.cart.service.CartService;
import com.galid.commerce.domains.member.domain.MemberEntity;
import com.galid.commerce.domains.member.domain.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {
    @InjectMocks
    private MemberService memberService;
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private CartService cartService;
    @Mock
    private PasswordEncoder encoder;

    @Test
    public void 회원가입() throws Exception {
        //given
        SignUpRequest request = new SignUpRequest("TEST", "TEST", "TEST", "TEST", "TEST", "TEST");
        Long fakeMemberId = 1l;
        MemberEntity member = createMember(request, fakeMemberId);
        given(memberRepository.findById(any()))
                .willReturn(Optional.ofNullable(member));
        given(memberRepository.save(any()))
                .willReturn(member);
        given(cartService.createCart(any()))
                .willReturn(any());

        //when
        Long userId = memberService.signUp(request);

        //then
        MemberEntity findMember = memberRepository.findById(userId).get();
        assertEquals(findMember.getAuthId(), request.getAuthId());
    }

    private MemberEntity createMember(SignUpRequest request, Long fakeMemberId) {
        MemberEntity newMember = MemberEntity.builder()
                .phone(request.getPhone())
                .authPw(request.getPassword())
                .authId(request.getAuthId())
                .address(new Address(request.getCity(), request.getStreet()))
                .name(request.getName())
                .build();
        ReflectionTestUtils.setField(newMember, "memberId", fakeMemberId);
        return newMember;
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        //given
        String duplicatedAuthId = "TEST";
        SignUpRequest request = new SignUpRequest(duplicatedAuthId, "TEST", "TEST", "TEST",
                "TEST", "TEST");
        MemberEntity member = createMember(request, 1l);
        given(memberRepository.findFirstByAuthId(duplicatedAuthId))
                .willReturn(Optional.of(member));

        // when, then
        assertThrows(IllegalStateException.class, () -> memberService.signUp(request));
    }

}