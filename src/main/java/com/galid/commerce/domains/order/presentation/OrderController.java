package com.galid.commerce.domains.order.presentation;

import com.galid.commerce.domains.member.domain.MemberEntity;
import com.galid.commerce.domains.order.service.OrderItemListInOrder;
import com.galid.commerce.domains.order.service.OrderRequest;
import com.galid.commerce.domains.order.service.OrderService;
import com.galid.commerce.domains.order.service.OrdererInfo;
import com.galid.commerce.infra.AuthenticationConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequiredArgsConstructor
@SessionAttributes
public class OrderController {
    private final OrderService orderService;
    private final AuthenticationConverter authenticationConverter;

    // 주문 페이지
    // 장바구니에 담긴 item id 들만을 받아옴
    // querydsl을 이용해, 유저의 장바구니의 itemId를 이용해, 한번에 dto로 받아옴 (조인문 연습하기)
    @PostMapping("/orders")
    public String getOrderPage(Authentication authentication,
                               Model model) {
        MemberEntity memberEntity = authenticationConverter.getMemberFromAuthentication(authentication);
        model.addAttribute("shippingInfo", memberEntity.getAddress());

        OrdererInfo ordererInfo = createOrdererInfo(memberEntity);
        model.addAttribute("ordererInfo", ordererInfo);

        OrderItemListInOrder orderItemListInOrder = orderService.getOrderItemListInOrder(memberEntity.getMemberId());
        model.addAttribute("orderItemList", orderItemListInOrder);

        return "orders/order";
    }

    private OrdererInfo createOrdererInfo(MemberEntity orderer) {
        return new OrdererInfo(orderer.getMemberId(),
                orderer.getName(),
                orderer.getPhone());
    }

    // 주문 요청 처리
    @PostMapping("/orders/order")
    public String order(Authentication authentication,
                        @ModelAttribute @Valid OrderRequest orderRequest) {
        MemberEntity member = authenticationConverter.getMemberFromAuthentication(authentication);

        Long orderId = orderService.order(member.getMemberId(), orderRequest);

        return "redirect:/orders/complete/" + orderId;
    }

    // 주문완료 페이지 요청
    @GetMapping("/orders/complete/{orderId}")
    public String getOrderCompletePage(@PathVariable("orderId") Long orderId,
                                       Model model) {
        model.addAttribute("orderId", orderId);
        model.addAttribute("orderDate", getOrderCompleteDate());
        return "orders/orderComplete";
    }

    private String getOrderCompleteDate() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(DateTimeFormatter.ISO_DATE);
    }
    
}
