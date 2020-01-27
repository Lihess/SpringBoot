package kr.co.fastcampus.eatgo.application;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import kr.co.fastcampus.eatgo.domain.MenuItem;
import kr.co.fastcampus.eatgo.domain.MenuItemRepository;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;

public class MenuItemServiceTests {
    
    private MenuItemService menuItemService;

    @Mock
    private MenuItemRepository menuItemRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        menuItemService = new MenuItemService(menuItemRepository);
    }

    @Test
    public void getMenuItems(){
        List<MenuItem> mockMenuItems = new ArrayList<>();
        mockMenuItems.add(MenuItem.builder().name("kimchi").build());

        given(menuItemRepository.findAllByRestaurantId(1004L)).willReturn(mockMenuItems);

        List<MenuItem> menuItems = menuItemService.getMenuItems(1004L);

        MenuItem menuItem = menuItems.get(0);
        assertThat(menuItem.getName(), is("kimchi"));
    }

    @Test
    public void bulkUpdate(){
        List<MenuItem> menuItems = new ArrayList<>();

        menuItemService.bulkUpdate(1L, menuItems);
    }
}