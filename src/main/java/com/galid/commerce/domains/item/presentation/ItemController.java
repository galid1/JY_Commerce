package com.galid.commerce.domains.item.presentation;

import com.galid.commerce.domains.item.domain.Book;
import com.galid.commerce.domains.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/items/new")
    public String getNewItemPage(Model model) {
        model.addAttribute("form", new BookForm());
        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String createItem(@ModelAttribute @Valid BookForm bookForm) {
        Long newItemId = itemService.saveItem(bookForm.toEntity());
        return "redirect:/items/details?item_id=" + newItemId;
    }
}
