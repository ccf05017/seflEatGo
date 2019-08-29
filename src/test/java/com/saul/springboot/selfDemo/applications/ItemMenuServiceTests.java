package com.saul.springboot.selfDemo.applications;

import com.saul.springboot.selfDemo.domain.ItemMenu;
import com.saul.springboot.selfDemo.domain.ItemMenuRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ItemMenuServiceTests {

    private ItemMenuService itemMenuService;

    @Mock
    ItemMenuRepository itemMenuRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        this.itemMenuService = new ItemMenuService(itemMenuRepository);
    }

    @Test
    public void bulkUpdate() {

        List<ItemMenu> itemMenus = new ArrayList<>();
        itemMenus.add(ItemMenu.builder().name("test1").build());
        itemMenus.add(ItemMenu.builder().name("test2").build());

        itemMenuService.bulkUpdate(1L, itemMenus);

        verify(itemMenuRepository, times(2)).save(any());
        // TODO: test case
        // 이 이후에 나오는 ItemMenu 객체들에 레스토랑 ID가 올바르게 들어갔는지 확인하는 테스트 케이스 어떻게 넣지?
    }

}
