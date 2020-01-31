package kr.co.fastcampus.eatgo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class MenuItem{
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    
    private Long restaurantId;
    
    @Transient
    @JsonInclude(JsonInclude.Include.NON_DEFAULT) // true 일때만 Json에 넣어주어라. 
    private Boolean destroy; // 삭제 여부을 위한 변수

	public boolean isDestroy() {
        if(this.destroy == null)
            return false;
        else return this.destroy;
	}
}