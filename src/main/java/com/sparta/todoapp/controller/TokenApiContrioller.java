package com.sparta.todoapp.controller;

import com.sparta.todoapp.dto.TokenRequest;
import com.sparta.todoapp.dto.TokenResponse;
import com.sparta.todoapp.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TokenApiContrioller {
    private final TokenService tokenService;

    @PostMapping("/apo/token")
    public ResponseEntity<TokenResponse> createNewAccessToken
            (@RequestBody TokenRequest request){
        String newAccessToken = tokenService.createNewAccessToken(request.getRefreshToken());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new TokenResponse(newAccessToken));
    }
}
