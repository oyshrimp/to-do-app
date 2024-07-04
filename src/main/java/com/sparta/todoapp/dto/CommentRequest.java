package com.sparta.todoapp.dto;

import com.sparta.todoapp.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequest {
    private Long scheduleId;
    private String content;
    private String password;
}
