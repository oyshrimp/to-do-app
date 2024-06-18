package com.sparta.todoapp.service;

import com.sparta.todoapp.dto.ScheduleCreateRequestDto;
import com.sparta.todoapp.dto.ScheduleResponseDto;
import com.sparta.todoapp.dto.ScheduleUpdateRequestDto;
import com.sparta.todoapp.entity.Schedule;
import com.sparta.todoapp.repository.ScheduleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ScheduleServiceTest {

    @Mock
    private ScheduleRepository scheduleRepository;

    @InjectMocks
    private ScheduleService scheduleService;

    @Test
    void createSchedule() {
        ScheduleCreateRequestDto createRequestDto = new ScheduleCreateRequestDto("user","password","title","content", LocalDateTime.now());
        //then
        assertDoesNotThrow(() -> scheduleService.createSchedule(createRequestDto));
    }

    @Test
    void findAll() {

        //given 테스트에서 쓸 데이터 설정
        List<Schedule> scheduleList = new ArrayList<>();
        scheduleList.add(Mockito.mock(Schedule.class));

        //when 대역 (외부 접근) -> repository에서 findAl l호출시 list 호출
        when(scheduleRepository.findAll()).thenReturn(scheduleList);

        //then 최종 return값 검증
        assertThat(scheduleService.findAll()).isNotEmpty();

    }

    @Test
    void findById() {

        Schedule schedule = new Schedule();

        when(scheduleRepository.findById(anyLong())).thenReturn(Optional.of(schedule));

        assertThat(scheduleService.findById(1L)).isNotNull();

    }

    @Test
    void updateSchedule() {

        Schedule schedule = new Schedule(1L,"user","password","title","content",LocalDateTime.now());
        ScheduleUpdateRequestDto scheduleUpdateRequestDto = new ScheduleUpdateRequestDto();

        when(scheduleRepository.findById(anyLong())).thenReturn(Optional.of(schedule));

        assertDoesNotThrow(() -> scheduleService.updateSchedule(1L,scheduleUpdateRequestDto,schedule.getPassword()));


    }

    @Test
    void deleteSchedule() {

        Schedule schedule = new Schedule(1L,"user","password","title","content",LocalDateTime.now());

        when(scheduleRepository.findById(anyLong())).thenReturn(Optional.of(schedule));

        assertDoesNotThrow(() -> scheduleService.deleteSchedule(1L,schedule.getPassword()));

    }
}