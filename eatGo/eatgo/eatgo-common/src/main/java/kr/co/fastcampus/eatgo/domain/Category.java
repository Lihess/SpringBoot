package kr.co.fastcampus.eatgo.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category{
    @Id
    @GeneratedValue
    private Long id;

    private String name;
}