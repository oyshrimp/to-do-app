package com.sparta.todoapp.service;

import com.sparta.todoapp.dto.AddUserRequest;
import com.sparta.todoapp.entity.User;
import com.sparta.todoapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(AddUserRequest dto) {
        String username = dto.getUsername();
        String password = dto.getPassword();

        if (!isValidUsername(username)) {
            throw new IllegalArgumentException("username은 최소 4자 이상, 10자 이하의 알파벳 소문자와 숫자로 이루어져야 합니다.");
        }

        if (!isValidPassword(password)) {
            throw new IllegalArgumentException("password는 최소 8자 이상, 15자 이하의 알파벳 대소문자와 숫자로 이루어져야 합니다.");
        }

        User user = User.builder()
                .username(username)
                .password(bCryptPasswordEncoder.encode(password))
                .build();

        return userRepository.save(user).getId();
    }

    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
    }

    private boolean isValidUsername(String username) {
        return username.matches("^[a-z0-9]{4,10}$");
    }

    private boolean isValidPassword(String password) {
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,15}$");
    }
}
