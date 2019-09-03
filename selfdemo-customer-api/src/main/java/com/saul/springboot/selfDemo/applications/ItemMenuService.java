package com.saul.springboot.selfDemo.applications;

import com.saul.springboot.selfDemo.domain.ItemMenu;
import com.saul.springboot.selfDemo.domain.ItemMenuNotFoundException;
import com.saul.springboot.selfDemo.domain.ItemMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
            // TODO
            // 현재 모든 PATCH 에 반응하고 있음
            // 삭제 케이스에서만 확인하도록 디버깅 필요
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

    public List<ItemMenu> getItemMenus() {
        List<ItemMenu> itemMenus = new ArrayList<>();

        return itemMenus;
    }
}
