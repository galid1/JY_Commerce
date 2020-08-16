package com.galid.commerce.domains.cart.presentation;

import com.galid.commerce.domains.cart.service.AddToCartRequestForm;
import com.galid.commerce.domains.cart.service.CartService;
import com.galid.commerce.domains.cart.service.ModifyCartLineRequestForm;
import com.galid.commerce.domains.item.domain.ItemEntity;
import com.galid.commerce.domains.item.domain.ItemRepository;
import com.galid.commerce.domains.member.domain.MemberEntity;
import com.galid.commerce.domains.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class CartController {
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final CartService cartService;

    @GetMapping("/carts")
    public String getCartPage(Authentication authentication,
                              Model model) {
        MemberEntity memberEntity = getMemberEntityByAuthentication(authentication);
        List<CartLineForm> cartLineFormList = toCartLineFormList(cartService.getCart(memberEntity.getMemberId()));

        model.addAttribute("cartLineList", cartLineFormList);

        return "carts/cart";
    }

    private List<CartLineForm> toCartLineFormList(Map<Long, Integer> cart) {
        return cart.entrySet()
                .stream()
                .map(entry -> {
                    ItemEntity itemEntity = itemRepository.findById(entry.getKey()).get();

                    return new CartLineForm(itemEntity.getItemId(),
                            itemEntity.getImagePath(),
                            itemEntity.getName(),
                            itemEntity.getPrice(),
                            entry.getValue());
                })
                .collect(Collectors.toList());
    }

    @PostMapping("/carts")
    @ResponseBody
    public ResponseEntity addToCart(@ModelAttribute AddToCartRequestForm addToCartRequestForm,
                            Authentication authentication) {
        MemberEntity memberEntity = getMemberEntityByAuthentication(authentication);

        cartService.addToCart(memberEntity.getMemberId(), addToCartRequestForm);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/carts")
    @ResponseBody
    public ResponseEntity modifyCartLine(@ModelAttribute ModifyCartLineRequestForm modifyCartLineRequestForm,
                                         Authentication authentication) {
        MemberEntity memberEntity = getMemberEntityByAuthentication(authentication);

        cartService.modifyCartLine(memberEntity.getMemberId(), modifyCartLineRequestForm);

        return ResponseEntity.ok().build();
    }

    // 현재 로그인한 유저 조회
    private MemberEntity getMemberEntityByAuthentication(Authentication authentication) {
        return memberRepository.findFirstByAuthId(authentication.getName()).get();
    }
}
