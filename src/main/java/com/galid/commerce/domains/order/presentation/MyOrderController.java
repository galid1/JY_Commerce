package com.galid.commerce.domains.order.presentation;

import com.galid.commerce.domains.member.service.MemberService;
import com.galid.commerce.domains.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class MyOrderController {
    private final MemberService memberService;
    private final OrderService orderService;

    @GetMapping("/my/orders")
    public String getMyOrderListPage() {
        return "orders/myOrderList";
    }

    @GetMapping("/my/orders/{orderId}")
    @ResponseBody
    public String getMyOrderDetails(@PathVariable("orderId") Long orderId) {
        return "OK";
    }
}
