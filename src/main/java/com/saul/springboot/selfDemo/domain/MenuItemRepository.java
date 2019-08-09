package com.saul.springboot.selfDemo.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MenuItemRepository extends CrudRepository<MenuItem, Long> {

    List<MenuItem> getMenuItemsById(Long id);

//    void addMenuItem(long l, String hot_periperi);
}
