package com.galid.commerce.domains.item.presentation;

import com.galid.commerce.domains.item.domain.ItemEntity;
import com.galid.commerce.domains.item.domain.ItemQuery;
import com.galid.commerce.domains.item.service.AddItemRequest;
import com.galid.commerce.domains.item.service.ItemSearchForm;
import com.galid.commerce.domains.item.service.ItemService;
import com.galid.commerce.domains.item.service.ItemSummaryInItemList;
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

    @GetMapping("/items")
    public String getItemListPage(@ModelAttribute ItemSearchForm searchForm,
                                  Model model) {
        // 아이템 검색 form
        if (searchForm == null)
            model.addAttribute("itemSearchForm", new ItemSearchForm());
        else
            model.addAttribute("itemSearchForm", searchForm);

        // 아이템 리스트
        List<ItemSummaryInItemList> items = itemQuery.searchItem(searchForm);
        model.addAttribute("items", items);

        return "items/itemList";
    }

    @GetMapping("/items/{itemId}")
    public String getItemDetailsPage(@PathVariable("itemId") Long itemId,
                                     Model model) {
        ItemEntity item = itemService.findItem(itemId);
        model.addAttribute("item", item);
        return "items/itemDetails";
    }
}
