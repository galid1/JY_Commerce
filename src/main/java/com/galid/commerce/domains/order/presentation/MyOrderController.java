package com.galid.commerce.domains.order.presentation;

import com.galid.commerce.domains.member.domain.MemberEntity;
import com.galid.commerce.domains.order.query.dto.MyOrderDetailsDto;
import com.galid.commerce.domains.order.query.dto.MyOrderSummaryDto;
import com.galid.commerce.domains.order.service.MyOrderService;
import com.galid.commerce.infra.AuthenticationConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class MyOrderController {
    private final MyOrderService myOrderService;
    private final AuthenticationConverter authenticationConverter;

    @GetMapping("/my/orders")
    public String getMyOrderListPage() {
        return "orders/myOrderList";
    }

    @GetMapping("/my/orders/{orderId}")
    public String getMyOrderDetails(@PathVariable("orderId") Long orderId,
                                    Model model) {
        MyOrderDetailsDto myOrderDetails = myOrderService.getMyOrderDetails(orderId);
        model.addAttribute("myOrderDetails", myOrderDetails);
        return "orders/myOrderDetails";
    }

    @GetMapping("/my/orders/delete/{orderId}")
    public String deleteMyOrder(Authentication authentication,
                                @PathVariable("orderId") Long orderId) {
        MemberEntity member = authenticationConverter.getMemberFromAuthentication(authentication);
        myOrderService.deleteMyOrder(member.getMemberId(), orderId);
        return "redirect:/my/orders";
    }
}
