package com.saul.springboot.selfDemo.domain;

import java.util.List;

public interface MenuItemRepository {

    List<MenuItem> getMenuItemsById(Long id);

    void addMenuItem(long l, String hot_periperi);
}
