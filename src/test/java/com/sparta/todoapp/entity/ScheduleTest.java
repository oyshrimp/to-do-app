//package com.sparta.todoapp.entity;
//
//import com.sparta.todoapp.dto.ScheduleUpdateRequestDto;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDateTime;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//
//class ScheduleTest {
//
//    @Test
//    void update() {
//
//        Schedule schedule =  new Schedule(1L,"user","password","title","content", LocalDateTime.now());
//        ScheduleUpdateRequestDto scheduleUpdateRequestDto = new ScheduleUpdateRequestDto();
//        schedule.update(scheduleUpdateRequestDto);
//
//       assertThat(schedule.getTitle()).isEqualTo(scheduleUpdateRequestDto.getTitle());
//       assertThat(schedule.getContent()).isEqualTo(scheduleUpdateRequestDto.getContent());
//
//    }
//}