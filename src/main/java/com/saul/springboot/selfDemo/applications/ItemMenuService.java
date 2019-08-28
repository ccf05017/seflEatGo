package com.saul.springboot.selfDemo.applications;

import com.saul.springboot.selfDemo.domain.ItemMenu;
import com.saul.springboot.selfDemo.domain.ItemMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemMenuService {

    @Autowired
    ItemMenuRepository itemMenuRepository;

    public ItemMenuService(ItemMenuRepository itemMenuRepository) {
        this.itemMenuRepository = itemMenuRepository;
    }

    public void bulkUpdate(Long restaurantId, List<ItemMenu> itemMenus) {

        for (ItemMenu itemMenu : itemMenus) {
            itemMenu.setRestaurantId(restaurantId);
            this.itemMenuRepository.save(itemMenu);
        }
    }

}
