package com.galid.commerce.domains.member.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/members")
public class MemberController {
    @GetMapping("/signUp")
    public String getSignUpPage() {
        return "members/signUp";
    }
}
