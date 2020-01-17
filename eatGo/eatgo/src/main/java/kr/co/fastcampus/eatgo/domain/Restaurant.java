package kr.co.fastcampus.eatgo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Restaurant{
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String address;

    @Transient // 임시, DB에 저장되지 않음
    private List<MenuItem> menuItems;

    public void addMenuItem(MenuItem menuItem){
        if(menuItems == null)
            menuItems = new ArrayList<>();
        menuItems.add(menuItem);
    }
}