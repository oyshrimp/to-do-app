package com.sparta.todoapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleUpdateRequestDto {
    private String title;
    private String content;
}
