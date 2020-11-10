package com.galid.commerce.domains.catalog.presentation;

import com.galid.commerce.domains.catalog.service.ReviewRequest;
import com.galid.commerce.domains.catalog.service.ReviewService;
import com.galid.commerce.domains.member.domain.MemberEntity;
import com.galid.commerce.infra.AuthenticationConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReviewRestController {
    private final AuthenticationConverter authenticationConverter;
    private final ReviewService reviewService;

    @PostMapping("/reviews/review")
    public Long writeReview(Authentication authentication,
                            @RequestBody ReviewRequest reviewRequest) {
        MemberEntity member = authenticationConverter.getMemberFromAuthentication(authentication);
        return reviewService.review(member.getMemberId(), reviewRequest);
    }
}
