package kr.co.fastcampus.eatgo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class User{
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String email;

    @NotEmpty
    private String name;

    private String password;
    
    @NotNull
    private Long level;

    public boolean isAdmin(){
        if(this.level >= 3)
            return true;
        else return false;
    }

	public boolean isActive() {
		return level > 0;
    }
    
    public void deativate(){
        level = 0L;
    }
}