package com.saul.springboot.selfDemo.interfaces;

import com.saul.springboot.selfDemo.applications.ItemMenuService;
import com.saul.springboot.selfDemo.domain.ItemMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemMenuController {

    @Autowired
    ItemMenuService itemMenuService;

    @PatchMapping("/restaurants/{restaurantId}/itemMenus")
    public String bulkUpdate(
            @PathVariable("restaurantId") Long restaurantId,
            @RequestBody List<ItemMenu> itemMenus
    ) {

        this.itemMenuService.bulkUpdate(restaurantId, itemMenus);

        return "";
    }

}
