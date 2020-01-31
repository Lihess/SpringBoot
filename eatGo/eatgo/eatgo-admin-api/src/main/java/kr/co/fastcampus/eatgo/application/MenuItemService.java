package kr.co.fastcampus.eatgo.application;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.fastcampus.eatgo.domain.MenuItem;
import kr.co.fastcampus.eatgo.domain.MenuItemRepository;

@Service
@Transactional
public class MenuItemService {
    private MenuItemRepository menuItemRepository;

    @Autowired
    public MenuItemService(MenuItemRepository menuItemRepository){
        this.menuItemRepository = menuItemRepository;
    }

	public void bulkUpdate(Long restaurantId, List<MenuItem> menuItems) {
        for(MenuItem menuItem : menuItems){
            update(restaurantId, menuItem);
        }
    }
    
    public void update(Long restaurantId, MenuItem menuItem){
        if(menuItem.isDestroy()){
            menuItemRepository.deleteById(menuItem.getId());
            return;
        }
        menuItem.setRestaurantId(restaurantId);
        menuItemRepository.save(menuItem);
    }

    public List<MenuItem> getMenuItems(Long restaurantId){
        return menuItemRepository.findAllByRestaurantId(restaurantId);     
    }
}

