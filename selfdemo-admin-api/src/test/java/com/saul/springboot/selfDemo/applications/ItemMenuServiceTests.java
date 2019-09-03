package com.saul.springboot.selfDemo.applications;

import com.saul.springboot.selfDemo.domain.ItemMenu;
import com.saul.springboot.selfDemo.domain.ItemMenuNotFoundException;
import com.saul.springboot.selfDemo.domain.ItemMenuRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

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
    public void bulkUpdateWithExistItemMenu() {

        List<ItemMenu> itemMenus = new ArrayList<>();

        // add case
        itemMenus.add(ItemMenu.builder().id(1L).name("test1").build());

        // update case
        itemMenus.add(ItemMenu.builder().id(2L).name("test2").build());

        // delete case
        // bulk 처리를 할 때는 이런 식으로 status 를 JSON 에 넣어서 처리하는 듯
        itemMenus.add(ItemMenu.builder().id(4444L).destroy(true).build());

        // 이거 넘나 더러운데..
        given(itemMenuRepository.findById(eq(1L)))
                .willReturn(java.util.Optional.ofNullable(ItemMenu.builder().id(1L).name("test1").build()));
        given(itemMenuRepository.findById(eq(2L)))
                .willReturn(java.util.Optional.ofNullable(ItemMenu.builder().id(2L).name("test2").build()));
        given(itemMenuRepository.findById(eq(4444L)))
                .willReturn(java.util.Optional.ofNullable(ItemMenu.builder().id(4444L).destroy(true).build()));

        itemMenuService.bulkUpdate(1L, itemMenus);

        verify(itemMenuRepository, times(2)).save(any());
        verify(itemMenuRepository, times(1)).deleteById(eq(4444L));
        // TODO: test case
        // 이 이후에 나오는 ItemMenu 객체들에 레스토랑 ID가 올바르게 들어갔는지 확인하는 테스트 케이스 어떻게 넣지?
    }

    @Test(expected = ItemMenuNotFoundException.class)
    public void bulkUpdateWithNonExistItemMenu() {

        List<ItemMenu> itemMenus = new ArrayList<>();

        // add case
        itemMenus.add(ItemMenu.builder().name("test1").build());

        // update case
        itemMenus.add(ItemMenu.builder().id(3L).name("test2").build());

        // delete case
        // bulk 처리를 할 때는 이런 식으로 status 를 JSON 에 넣어서 처리하는 듯
        itemMenus.add(ItemMenu.builder().id(4444L).destroy(true).build());

        itemMenuService.bulkUpdate(1L, itemMenus);

        verify(itemMenuRepository, times(1)).deleteById(eq(4444L));
    }

    @Test
    public void getItemMenus() {

        List<ItemMenu> itemMenus = new ArrayList<>();
        itemMenus.add(ItemMenu.builder().name("sushi").restaurantId(1L).build());

        given(itemMenuRepository.findAllByRestaurantId(eq(1L))).willReturn(itemMenus);

        List<ItemMenu> returnedMenus = itemMenuService.getItemMenus(1L);
        ItemMenu target = returnedMenus.get(0);

        assertThat(target.getName(), is("sushi"));
    }

}
