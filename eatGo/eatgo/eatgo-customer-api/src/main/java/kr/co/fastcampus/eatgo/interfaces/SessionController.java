package kr.co.fastcampus.eatgo.interfaces;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.fastcampus.eatgo.application.UserService;
import kr.co.fastcampus.eatgo.domain.SessionResponseDto;
import kr.co.fastcampus.eatgo.domain.User;

@RestController
public class SessionController {
    @Autowired
    private UserService userSerivce;


    @PostMapping("/seesion")
    public ResponseEntity<SessionResponseDto> create(
        @RequestBody User resource
    ) throws URISyntaxException {
        String accessToken = "ACCESSTOKEN";

        String email = resource.getEmail();
        String password = resource.getPassword();
        userSerivce.authenticate(email, password);

        SessionResponseDto sessionDto = SessionResponseDto.builder().accessToken(accessToken).build();
        
        String url = "/session";
        return ResponseEntity.created(new URI(url)).body(sessionDto);
    }
}