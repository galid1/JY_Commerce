package com.galid.commerce.domains.catalog.presentation;

import com.galid.commerce.domains.catalog.domain.item.ItemEntity;
import com.galid.commerce.domains.catalog.domain.item.ItemQuery;
import com.galid.commerce.domains.catalog.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    private final ItemQuery itemQuery;

    @GetMapping("/items/new")
    public String getNewItemPage(Model model) {
        model.addAttribute("form", new AddItemRequest());
        return "items/registerItemForm";
    }

    @PostMapping("/items/new")
    public String createItem(@ModelAttribute @Valid AddItemRequest addItemRequest) {
        Long newItemId = itemService.saveItem(addItemRequest);
        return "redirect:/items/"+ newItemId;
    }

    @GetMapping("/items/{itemId}")
    public String getItemDetailsPage(@PathVariable("itemId") Long itemId,
                                     Model model) {
        ItemDetails item = itemService.findItem(itemId);
        model.addAttribute("item", item);
        return "items/itemDetails";
    }
}
