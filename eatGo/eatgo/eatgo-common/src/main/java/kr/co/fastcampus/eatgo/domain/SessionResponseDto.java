// 순수하게 데이터만 가짐
package kr.co.fastcampus.eatgo.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SessionResponseDto{
    private String accessToken;
}