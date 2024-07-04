package com.sparta.todoapp.service;

import com.sparta.todoapp.dto.ScheduleCreateRequestDto;
import com.sparta.todoapp.dto.ScheduleUpdateRequestDto;
import com.sparta.todoapp.entity.Schedule;
import com.sparta.todoapp.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    // 일정 작성
    public void createSchedule(ScheduleCreateRequestDto requestDto) {
        Schedule schedule = new Schedule(requestDto);
        scheduleRepository.save(schedule);
    }

    // 전체 일정 조회
    public List<Schedule> findAllSchedules() {
        return scheduleRepository.findAll();
    }

    // 선택 일정 조회
    public Schedule findScheduleById(Long id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 일정 ID입니다."));
    }

    // 일정 수정
    public Schedule updateSchedule(Long id, ScheduleUpdateRequestDto requestDto, String password) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 일정 ID입니다."));

        if (!schedule.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        schedule.update(requestDto);
        return scheduleRepository.save(schedule);
    }

    // 일정 삭제
    public void deleteSchedule(Long id, String password) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 일정 ID입니다."));

        if (!schedule.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        scheduleRepository.delete(schedule);
    }
}
