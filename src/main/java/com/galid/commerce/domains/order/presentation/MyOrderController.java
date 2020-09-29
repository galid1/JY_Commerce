package com.galid.commerce.domains.order.presentation;

import com.galid.commerce.domains.member.domain.MemberEntity;
import com.galid.commerce.domains.member.service.MemberService;
import com.galid.commerce.domains.order.service.MyOrderService;
import com.galid.commerce.domains.order.service.OrderService;
import com.galid.commerce.infra.AuthenticationConverter;
import lombok.RequiredArgsConstructor;
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
        model.addAttribute("myOrders", myOrderService.getMyOrderSummary(member.getMemberId()));
        return "orders/myOrderList";
    }

    @GetMapping("/my/orders/{orderId}")
    @ResponseBody
    public String getMyOrderDetails(@PathVariable("orderId") Long orderId) {
        return "OK";
    }
}
