package com.sparta.todoapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserRequest {
    private String username;
    private String password;
}
