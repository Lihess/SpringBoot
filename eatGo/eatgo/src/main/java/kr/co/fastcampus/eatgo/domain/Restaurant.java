package kr.co.fastcampus.eatgo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant{
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String address;

    @Transient // 임시, DB에 저장되지 않음
    private List<MenuItem> menuItems = new ArrayList<MenuItem>();    

    public Restaurant(Long id, String name, String address){
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public void addMenuItem(MenuItem menuItem){
        menuItems.add(menuItem);
    }

    public void setMenuItems(List<MenuItem> menuItems){
        for(MenuItem menuItem : menuItems)
            addMenuItem(menuItem);
    }
}