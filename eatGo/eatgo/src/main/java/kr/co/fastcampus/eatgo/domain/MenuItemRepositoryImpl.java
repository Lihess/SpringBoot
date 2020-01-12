package kr.co.fastcampus.eatgo.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class MenuItemRepositoryImpl implements MenuItemRepository {
    List<MenuItem> menuItems = new ArrayList<>();

    MenuItemRepositoryImpl(){
        menuItems.add(new MenuItem("Kimchi"));
    }

    @Override
    public List<MenuItem> findAllByRestauranId(Long id) {
        return menuItems;
    }

}