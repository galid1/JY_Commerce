package com.galid.commerce.domains.catalog.presentation;

import com.galid.commerce.domains.catalog.query.application.MyReviewService;
import com.galid.commerce.domains.catalog.query.dto.MyReviewSummary;
import com.galid.commerce.domains.member.domain.MemberEntity;
import com.galid.commerce.infra.AuthenticationConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MyReviewRestController {
    private final AuthenticationConverter authenticationConverter;
    private final MyReviewService myReviewService;

    @GetMapping("/api/my/reviews")
    public MyReviewSummary getMyReviewSummary(Authentication authentication,
                                              Pageable pageable) {
        MemberEntity member = authenticationConverter.getMemberFromAuthentication(authentication);
        return myReviewService.myReviewSummary(member.getMemberId(), pageable);
    }
}
