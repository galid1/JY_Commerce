package com.galid.commerce.domains;

import com.galid.commerce.domains.catalog.domain.item.ItemQuery;
import com.galid.commerce.domains.catalog.service.CategoryService;
import com.galid.commerce.domains.catalog.service.ItemSearchForm;
import com.galid.commerce.domains.catalog.service.ItemSummaryInItemList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final ItemQuery itemQuery;
    private final CategoryService categoryService;

    @GetMapping("/main")
    public String getMainPage(@ModelAttribute ItemSearchForm searchForm,
                              Model model) {
        // category
        model.addAttribute("rootCategory", categoryService.createCategoryRoot());

        // 아이템 검색 form
        if (searchForm == null)
            model.addAttribute("itemSearchForm", new ItemSearchForm());
        else
            model.addAttribute("itemSearchForm", searchForm);

        // 아이템 리스트
        List<ItemSummaryInItemList> items = itemQuery.searchItem(searchForm);
        model.addAttribute("items", items);

        return "main";
    }
}
