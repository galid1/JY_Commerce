package com.galid.commerce.domains.member.presentation;

import com.galid.commerce.domains.member.service.MemberService;
import com.galid.commerce.domains.member.service.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/signUp")
    public String getSignUpPage() {
        return "members/signUp";
    }

    @PostMapping("/signUp")
    public String signUp(@ModelAttribute SignUpRequest request) {
        memberService.signUp(request);
        return "redirect:/signIn";
    }

    @GetMapping("/signIn")
    public String getSignInPage() {
        return "members/signIn";
    }
    
    // signIn 처리는 Spring security
}
