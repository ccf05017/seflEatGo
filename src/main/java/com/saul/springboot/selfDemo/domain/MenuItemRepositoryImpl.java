package com.saul.springboot.selfDemo.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MenuItemRepositoryImpl implements MenuItemRepository {

    private List<MenuItem> menuItems = new ArrayList<>();

    public List<MenuItem> getMenuItems() {
        return this.menuItems;
    }

    public MenuItemRepositoryImpl() {
        menuItems.add(new MenuItem(3333L, "periperi"));
        menuItems.add(new MenuItem(3333L, "francesinha"));
        menuItems.add(new MenuItem(4444L, "pulledfork"));
        menuItems.add(new MenuItem(4444L, "slider"));
    }

    @Override
    public List<MenuItem> getMenuItemsById(Long id) {

        List<MenuItem> menuItems = this.menuItems.stream()
            .filter(menuItemObject -> menuItemObject.getRestaurantId().equals(id))
            .collect(Collectors.toList());

        return menuItems;
    }

    @Override
    public void addMenuItem(long restaurantId, String menuName) {

        MenuItem menuItem = new MenuItem(restaurantId, menuName);
        menuItems.add(menuItem);
    }
}
