package com.saul.springboot.selfDemo.domain;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
public class MenuItemRepositoryImplTests {

    @SpyBean(MenuItemRepositoryImpl.class)
    MenuItemRepository menuItemRepository;

    @Test
    public void getMenuItems() {
        this.menuItemRepository.addMenuItem(3333L, "hot periperi");

        List<MenuItem> menuItems =  this.menuItemRepository.getMenuItemsById(3333L);
        assertThat(menuItems.get(0).getMenuName(), is("periperi"));
        assertThat(menuItems.get(1).getMenuName(), is("francesinha"));
        assertThat(menuItems.get(2).getMenuName(), is("hot periperi"));
    }
}
