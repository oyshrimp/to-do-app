package com.sparta.todoapp.service;

import com.sparta.todoapp.config.jwt.TokenProvider;
import com.sparta.todoapp.entity.RefreshToken;
import com.sparta.todoapp.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class TokenService {

    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    public String createNewAccessToken(String refreshToken) {

        if (!tokenProvider.validToken(refreshToken)) {
            throw new IllegalArgumentException("Unexpected token");
        }

        RefreshToken refreshTokenEntity = refreshTokenService.findByRefreshToken(refreshToken);
        User user = refreshTokenEntity.getUser(); // RefreshToken에서 User 객체 가져오기

        return tokenProvider.generateToken(user, Duration.ofHours(2));
    }
}
