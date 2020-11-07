package com.galid.commerce.domains.cart.presentation;

import com.galid.commerce.domains.cart.query.dto.CartLineDto;
import com.galid.commerce.domains.cart.service.AddToCartRequestForm;
import com.galid.commerce.domains.cart.service.CartService;
import com.galid.commerce.domains.cart.service.ModifyOrderCountRequestForm;
import com.galid.commerce.domains.member.domain.MemberEntity;
import com.galid.commerce.infra.AuthenticationConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final AuthenticationConverter authenticationConverter;

    @GetMapping("/carts")
    public String getCartPage(Authentication authentication,
                              Model model) {
        Long memberId = authenticationConverter.getMemberFromAuthentication(authentication)
                .getMemberId();
        List<CartLineDto> cartLineDtoInCartPage = cartService.getCartInCartPage(memberId);

        model.addAttribute("cartLineList", cartLineDtoInCartPage);

        return "carts/cart";
    }

    @PostMapping("/carts")
    public String addItemToCart(@ModelAttribute @Valid AddToCartRequestForm addToCartRequestForm,
                                Authentication authentication) {
        MemberEntity memberEntity = authenticationConverter.getMemberFromAuthentication(authentication);

        cartService.addItemToCart(memberEntity.getMemberId(), addToCartRequestForm);

        return "redirect:/carts";
    }

    @PutMapping("/carts")
    @ResponseBody
    public ResponseEntity modifyCartLine(@ModelAttribute ModifyOrderCountRequestForm modifyOrderCountRequestForm,
                                         Authentication authentication) {
        MemberEntity memberEntity = authenticationConverter.getMemberFromAuthentication(authentication);

        cartService.modifyOrderCount(memberEntity.getMemberId(), modifyOrderCountRequestForm);

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
