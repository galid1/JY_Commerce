package com.galid.commerce.domains.cart.presentation;

import com.galid.commerce.domains.cart.service.AddToCartRequestForm;
import com.galid.commerce.domains.cart.service.CartLineForm;
import com.galid.commerce.domains.cart.service.CartService;
import com.galid.commerce.domains.cart.service.ModifyCartLineRequestForm;
import com.galid.commerce.domains.item.domain.ItemRepository;
import com.galid.commerce.domains.member.domain.MemberEntity;
import com.galid.commerce.domains.member.domain.MemberRepository;
import com.galid.commerce.infra.AuthenticationConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CartController {
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final CartService cartService;
    private final AuthenticationConverter authenticationConverter;

    @GetMapping("/carts")
    public String getCartPage(Authentication authentication,
                              Model model) {
        Long memberId = authenticationConverter.getMemberFromAuthentication(authentication)
                .getMemberId();
        List<CartLineForm> cartLineFormsInCartPage = cartService.getCartInCartPage(memberId);

        model.addAttribute("cartLineList", cartLineFormsInCartPage);

        return "carts/cart";
    }

    @PostMapping("/carts")
    public String addItemToCart(@ModelAttribute AddToCartRequestForm addToCartRequestForm,
                            Authentication authentication) {
        MemberEntity memberEntity = authenticationConverter.getMemberFromAuthentication(authentication);

        cartService.addItemToCart(memberEntity.getMemberId(), addToCartRequestForm);

        return "redirect:/carts";
    }

    @PutMapping("/carts")
    @ResponseBody
    public ResponseEntity modifyCartLine(@ModelAttribute ModifyCartLineRequestForm modifyCartLineRequestForm,
                                         Authentication authentication) {
        MemberEntity memberEntity = authenticationConverter.getMemberFromAuthentication(authentication);

        cartService.modifyCartLine(memberEntity.getMemberId(), modifyCartLineRequestForm);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/carts")
    @ResponseBody
    public ResponseEntity deleteCartLine(@RequestParam("itemId") Long itemId,
                                         Authentication authentication) {
        MemberEntity member = authenticationConverter.getMemberFromAuthentication(authentication);
        cartService.removeCartLine(member.getMemberId(), itemId);
        return ResponseEntity.ok().build();
    }
}
