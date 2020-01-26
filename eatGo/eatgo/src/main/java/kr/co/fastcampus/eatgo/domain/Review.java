package kr.co.fastcampus.eatgo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Review {
    @Id
    @GeneratedValue
    private Long id;

    private Long restaurantId;

    @NotEmpty
    private String name;

    @Max(5)
    @Min(0)
    private Integer score;

    @NotEmpty
    private String description;
}
