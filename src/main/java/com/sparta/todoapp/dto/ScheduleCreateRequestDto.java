package com.sparta.todoapp.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ScheduleCreateRequestDto {

    private String user;
    private String password;
    private String title;
    private String content;
    private LocalDateTime scheduleDate;


    }

