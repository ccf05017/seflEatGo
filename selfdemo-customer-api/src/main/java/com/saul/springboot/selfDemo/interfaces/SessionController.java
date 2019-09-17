package com.saul.springboot.selfDemo.interfaces;

import com.saul.springboot.selfDemo.applications.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class SessionController {

    @Autowired
    UserService userService;

    @PostMapping("/session")
    public ResponseEntity<SessionResponseDto> login(
            @RequestBody SessionRequestDto sessionRequestDto
    ) throws URISyntaxException {

        String accessToken = "ACCESSTOKEN";
        userService.authenticate(sessionRequestDto.getEmail(), sessionRequestDto.getPassword());

        SessionResponseDto sessionResponseDto = SessionResponseDto.builder().accessToken(accessToken).build();

        String url = "/session";

        return ResponseEntity.created(new URI(url)).body(sessionResponseDto);

    }

}
