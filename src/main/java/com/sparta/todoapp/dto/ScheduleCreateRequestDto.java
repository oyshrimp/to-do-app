package com.sparta.todoapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ScheduleCreateRequestDto {
    private String user;
    private String password;
    private String title;
    private String content;
    private LocalDateTime scheduleDate;
}
