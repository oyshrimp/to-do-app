package com.sparta.todoapp.dto;

import com.sparta.todoapp.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {

    private String user;
    private String title;
    private String content;
    private LocalDateTime scheduleDate;

    public ScheduleResponseDto(Schedule schedule) {
        this.user = schedule.getUser();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.scheduleDate = schedule.getCreatedAt();
    }
}