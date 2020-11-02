package com.galid.commerce.domains.catalog.presentation;

import com.galid.commerce.domains.catalog.service.ItemService;
import com.galid.commerce.domains.catalog.service.ReviewRequest;
import com.galid.commerce.domains.catalog.service.ReviewService;
import com.galid.commerce.domains.member.domain.MemberEntity;
import com.galid.commerce.infra.AuthenticationConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ReviewController {
    private final AuthenticationConverter authenticationConverter;
    private final ItemService itemService;
    private final ReviewService reviewService;

    @GetMapping("/reviews/review")
    public String getReviewPage(@RequestParam(value = "productId", required = true) Long productId,
                                Model model) {
        model.addAttribute("product", itemService.findItem(productId));
        return "reviews/review";
    }

    @ResponseBody
    @PostMapping("/reviews/review")
    public Long writeReview(Authentication authentication,
                            @RequestBody ReviewRequest reviewRequest) {
        MemberEntity member = authenticationConverter.getMemberFromAuthentication(authentication);
        return reviewService.review(member.getMemberId(), reviewRequest);
    }
}
