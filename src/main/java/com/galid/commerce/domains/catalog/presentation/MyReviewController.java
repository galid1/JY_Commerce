package com.galid.commerce.domains.catalog.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MyReviewController {
    @GetMapping("/my/reviews")
    public String getMyReviewListPage() {
        return "reviews/myReviewList";
    }
}
