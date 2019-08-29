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
import static org.mockito.ArgumentMatchers.eq;
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

        // add case
        itemMenus.add(ItemMenu.builder().name("test1").build());

        // update case
        itemMenus.add(ItemMenu.builder().id(3L).name("test2").build());

        // delete case
        // bulk 처리를 할 때는 이런 식으로 status 를 JSON 에 넣어서 처리하는 듯
        itemMenus.add(ItemMenu.builder().id(4444L).destroy(true).build());

        itemMenuService.bulkUpdate(1L, itemMenus);

        verify(itemMenuRepository, times(2)).save(any());
        verify(itemMenuRepository, times(1)).deleteById(eq(4444L));
        // TODO: test case
        // 이 이후에 나오는 ItemMenu 객체들에 레스토랑 ID가 올바르게 들어갔는지 확인하는 테스트 케이스 어떻게 넣지?
    }

}
