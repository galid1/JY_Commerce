package com.galid.commerce.domains.order.presentation;

import com.galid.commerce.domains.member.domain.MemberEntity;
import com.galid.commerce.domains.order.query.dto.MyOrderSummaryDto;
import com.galid.commerce.domains.order.service.MyOrderService;
import com.galid.commerce.infra.AuthenticationConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MyOrderRestController {
    private final AuthenticationConverter authenticationConverter;
    private final MyOrderService myOrderService;

    @GetMapping("/api/my/orders")
    public MyOrderSummaryDto getMoreOrderList(Authentication authentication,
                                              Pageable pageable) {
        MemberEntity member = authenticationConverter.getMemberFromAuthentication(authentication);
        MyOrderSummaryDto myOrderSummaryDto = myOrderService.getMyOrderSummary(member.getMemberId(), pageable);
        return myOrderSummaryDto;
    }
}
