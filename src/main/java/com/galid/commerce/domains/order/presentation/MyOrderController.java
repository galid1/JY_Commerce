package com.galid.commerce.domains.order.presentation;

import com.galid.commerce.domains.member.domain.MemberEntity;
import com.galid.commerce.domains.member.service.MemberService;
import com.galid.commerce.domains.order.query.dto.MyOrderSummaryDto;
import com.galid.commerce.domains.order.service.MyOrderService;
import com.galid.commerce.domains.order.service.OrderService;
import com.galid.commerce.infra.AuthenticationConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class MyOrderController {
    private final MemberService memberService;
    private final OrderService orderService;
    private final MyOrderService myOrderService;
    private final AuthenticationConverter authenticationConverter;

    @GetMapping("/my/orders")
    public String getMyOrderListPage(Authentication authentication,
                                     Model model) {
        MemberEntity member = authenticationConverter.getMemberFromAuthentication(authentication);
        MyOrderSummaryDto myOrderSummary = myOrderService.getMyOrderSummary(member.getMemberId(), PageRequest.of(0, 20));
        model.addAttribute("myOrderSummary", myOrderSummary);
        return "orders/myOrderList";
    }

    @GetMapping("/my/orders/{orderId}")
    @ResponseBody
    public String getMyOrderDetails(@PathVariable("orderId") Long orderId) {
        return "OK";
    }
}
