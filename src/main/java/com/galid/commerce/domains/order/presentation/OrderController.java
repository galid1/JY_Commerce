package com.galid.commerce.domains.order.presentation;

import com.galid.commerce.domains.member.domain.MemberEntity;
import com.galid.commerce.domains.order.query.dao.OrderDao;
import com.galid.commerce.domains.order.query.dto.OrderSummaryDto;
import com.galid.commerce.domains.order.query.dto.OrdererInfoDto;
import com.galid.commerce.domains.order.service.OrderRequest;
import com.galid.commerce.domains.order.service.OrderService;
import com.galid.commerce.infra.AuthenticationConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderDao orderDao;
    private final AuthenticationConverter authenticationConverter;

    // 주문 페이지
    // 장바구니에 담긴 item id 들만을 받아옴
    @PostMapping("/orders")
    public String getOrderPage(Authentication authentication,
                               @ModelAttribute OrderRequest orderRequest,
                               Model model) {
        MemberEntity memberEntity = authenticationConverter.getMemberFromAuthentication(authentication);
        model.addAttribute("shippingInfo", memberEntity.getAddress());

        OrdererInfoDto ordererInfoDto = createOrdererInfo(memberEntity);
        model.addAttribute("ordererInfo", ordererInfoDto);

        List<Long> orderItemIdList = orderRequest.getOrderLineList()
                .stream()
                .map(ol -> ol.getItemId())
                .collect(Collectors.toList());
        OrderSummaryDto orderSummaryDto = orderDao.getOrderSummary(memberEntity.getMemberId(), orderItemIdList);
        model.addAttribute("orderSummary", orderSummaryDto);

        return "orders/order";
    }

    private OrdererInfoDto createOrdererInfo(MemberEntity orderer) {
        return new OrdererInfoDto(orderer.getMemberId(),
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
