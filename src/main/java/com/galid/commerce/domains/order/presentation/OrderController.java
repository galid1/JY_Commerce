package com.galid.commerce.domains.order.presentation;

import com.galid.commerce.domains.catalog.domain.item.ItemEntity;
import com.galid.commerce.domains.catalog.domain.item.ItemRepository;
import com.galid.commerce.domains.member.domain.MemberEntity;
import com.galid.commerce.domains.order.query.dao.OrderDao;
import com.galid.commerce.domains.order.query.dto.OrderItemDto;
import com.galid.commerce.domains.order.query.dto.OrderSummaryDto;
import com.galid.commerce.domains.order.query.dto.OrdererInfoDto;
import com.galid.commerce.domains.order.service.OrderLineRequest;
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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderDao orderDao;
    private final ItemRepository itemRepository;
    private final AuthenticationConverter authenticationConverter;

    @ModelAttribute
    public void setOrderInfo(Authentication authentication,
                             Model model) {
        MemberEntity memberEntity = authenticationConverter.getMemberFromAuthentication(authentication);
        model.addAttribute("shippingInfo", memberEntity.getAddress());

        OrdererInfoDto ordererInfoDto = createOrdererInfo(memberEntity);
        model.addAttribute("ordererInfo", ordererInfoDto);
    }

    // 주문 페이지
    // 장바구니에 담긴 item id 들만을 받아옴
    @PostMapping("/orders")
    public String getOrderPage(Authentication authentication,
                               @ModelAttribute OrderRequest orderRequest,
                               Model model) {
        MemberEntity memberEntity = authenticationConverter.getMemberFromAuthentication(authentication);

        List<Long> orderItemIdList = orderRequest.getOrderLineList()
                .stream()
                .map(ol -> ol.getItemId())
                .collect(Collectors.toList());
        OrderSummaryDto orderSummaryDto = orderDao.getOrderSummaryInCart(memberEntity.getMemberId(), orderItemIdList);
        model.addAttribute("orderSummary", orderSummaryDto);

        return "orders/order";
    }

    // 바로구매
    @PostMapping("/orders/direct")
    public String getOrderPageByDirect(@ModelAttribute OrderRequest orderRequest,
                                       Model model) {
        model.addAttribute("orderSummary", createOrderSummary(orderRequest));
        return "orders/order";
    }

    // 바로구매 요청의 OrderSummary를 생성
    private OrderSummaryDto createOrderSummary(OrderRequest orderRequest) {
        // 바로구매시 하나의 아이템만을 구매하게 되므로 첫번째 인덱스의 아이템을 이용
        OrderLineRequest orderLineRequest = orderRequest.getOrderLineList().get(0);

        ItemEntity itemEntity = itemRepository.findById(orderLineRequest.getItemId())
                .get();
        OrderItemDto orderItemDto = new OrderItemDto(itemEntity.getItemId(),
                itemEntity.getName(),
                itemEntity.getPrice(),
                orderLineRequest.getOrderCount());

        return new OrderSummaryDto(Arrays.asList(orderItemDto));
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
