package kr.co.fastcampus.eatgo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

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

    @NotNull
    private String name;

    @NotNull
    private String address;

    @Transient // 임시, DB에 저장되지 않음
    @JsonInclude(JsonInclude.Include.NON_NULL) // null이 아닐때만 넣어줌!
    private List<MenuItem> menuItems;

    public void addMenuItem(MenuItem menuItem){
        if(menuItems == null)
            menuItems = new ArrayList<>();
        menuItems.add(menuItem);
    }
}