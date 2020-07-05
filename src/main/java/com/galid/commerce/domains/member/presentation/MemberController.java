package com.galid.commerce.domains.member.presentation;

import com.galid.commerce.domains.member.service.MemberService;
import com.galid.commerce.domains.member.service.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/auth/signUp")
    public String getSignUpPage() {
        return "members/signUp";
    }

    @PostMapping("/auth/signUp")
    public String signUp(@ModelAttribute @Valid SignUpRequest request) {
        memberService.signUp(request);
        return "redirect:/auth/signIn";
    }

    @GetMapping("/auth/signIn")
    public String getSignInPage() {
        return "members/signIn";
    }

    // signIn 처리는 Spring security
    @GetMapping("/auth/signInError")
    public String getSignInPageWithError(Model model) {
        model.addAttribute("loginError", "true");
        return "members/signIn";
    }

}
