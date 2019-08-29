package com.saul.springboot.selfDemo.applications;

import com.saul.springboot.selfDemo.domain.ItemMenu;
import com.saul.springboot.selfDemo.domain.ItemMenuNotFoundException;
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

            isExist(itemMenu);
            update(restaurantId, itemMenu);
        }
    }

    private void isExist(ItemMenu itemMenu) {
        this.itemMenuRepository
            .findById(itemMenu.getId())
            .orElseThrow(() -> new ItemMenuNotFoundException(itemMenu.getId()));
    }

    private void update(Long restaurantId, ItemMenu itemMenu) {

        if(itemMenu.isDestroy()) {
            this.itemMenuRepository.deleteById(itemMenu.getId());
            return;
        }

        itemMenu.setRestaurantId(restaurantId);
        this.itemMenuRepository.save(itemMenu);
    }

}
