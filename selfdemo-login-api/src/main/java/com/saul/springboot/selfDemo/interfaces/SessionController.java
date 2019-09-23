package com.saul.springboot.selfDemo.interfaces;

import com.saul.springboot.selfDemo.applications.UserService;
import com.saul.springboot.selfDemo.domain.User;
import com.saul.springboot.selfDemo.utils.JwtUtil;
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

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/session")
    public ResponseEntity<SessionResponseDto> login(
            @RequestBody SessionRequestDto sessionRequestDto
    ) throws URISyntaxException {

        User authenticatedUser = userService.authenticate(
                sessionRequestDto.getEmail(),
                sessionRequestDto.getPassword());

        SessionResponseDto sessionResponseDto = SessionResponseDto.builder()
                .accessToken(jwtUtil.createToken(
                        authenticatedUser.getId(),
                        authenticatedUser.getName(),
                        authenticatedUser.isRestaurantOwner() ? authenticatedUser.getRestaurantId() : null
                ))
                .build();

        String url = "/session";

        return ResponseEntity.created(new URI(url)).body(sessionResponseDto);
    }

}
