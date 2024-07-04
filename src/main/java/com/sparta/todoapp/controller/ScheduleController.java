package com.sparta.todoapp.controller;

import com.sparta.todoapp.dto.ScheduleCreateRequestDto;
import com.sparta.todoapp.dto.ScheduleResponseDto;
import com.sparta.todoapp.dto.ScheduleUpdateRequestDto;
import com.sparta.todoapp.entity.Schedule;
import com.sparta.todoapp.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 일정 작성
    @PostMapping("/schedules")
    public ResponseEntity<Void> createSchedule(@RequestBody ScheduleCreateRequestDto requestDto) {
        scheduleService.createSchedule(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 전체 일정 조회
    @GetMapping("/schedules")
    public ResponseEntity<List<ScheduleResponseDto>> findAllSchedules() {
        List<ScheduleResponseDto> schedules = scheduleService.findAllSchedules()
                .stream()
                .map(ScheduleResponseDto::new)
                .toList();

        return ResponseEntity.ok()
                .body(schedules);
    }

    // 선택 일정 조회
    @GetMapping("/schedules/{id}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable Long id) {
        Schedule schedule = scheduleService.findScheduleById(id);
        return ResponseEntity.ok()
                .body(new ScheduleResponseDto(schedule));
    }

    // 일정 수정
    @PutMapping("/schedules/{id}")
    public ResponseEntity<Schedule> updateSchedule(@PathVariable Long id,
                                                   @RequestBody ScheduleUpdateRequestDto requestDto,
                                                   @RequestParam String password) {
        Schedule updatedSchedule = scheduleService.updateSchedule(id, requestDto, password);
        return ResponseEntity.ok().body(updatedSchedule);
    }

    // 일정 삭제
    @DeleteMapping("/schedules/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id, @RequestParam String password) {
        scheduleService.deleteSchedule(id, password);
        return ResponseEntity.noContent().build();
    }
}
