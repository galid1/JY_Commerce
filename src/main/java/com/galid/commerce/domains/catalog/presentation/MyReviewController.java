package com.galid.commerce.domains.catalog.presentation;

import com.galid.commerce.domains.catalog.query.application.MyReviewService;
import com.galid.commerce.domains.catalog.query.dto.MyReviewSummary;
import com.galid.commerce.domains.member.domain.MemberEntity;
import com.galid.commerce.infra.AuthenticationConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MyReviewController {
    private final AuthenticationConverter authenticationConverter;
    private final MyReviewService myReviewService;

    @GetMapping("/my/reviews")
    public String getMyReviewListPage(Authentication authentication,
                                      Model model) {
        MemberEntity member = authenticationConverter.getMemberFromAuthentication(authentication);
        MyReviewSummary myReviewSummary = myReviewService.myReviewSummary(member.getMemberId(), PageRequest.of(0, 20));
        model.addAttribute("myReviewSummary", myReviewSummary);
        return "reviews/myReviewList";
    }
}
