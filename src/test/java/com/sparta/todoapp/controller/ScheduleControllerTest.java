//package com.sparta.todoapp.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.sparta.todoapp.dto.ScheduleCreateRequestDto;
//import com.sparta.todoapp.dto.ScheduleUpdateRequestDto;
//import com.sparta.todoapp.entity.Schedule;
//import com.sparta.todoapp.repository.ScheduleRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//
//class ScheduleControllerTest {
//
//    @Autowired
//    protected MockMvc mockMvc;
//
//    @Autowired
//    protected ObjectMapper objectMapper;
//
//    @Autowired
//    private WebApplicationContext context;
//
//    @Autowired
//    ScheduleRepository scheduleRepository;
//
//    @BeforeEach
//    public void mockMvcSetup(){
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
//                .build();
//        scheduleRepository.deleteAll();
//    }
//
//    // 일정 작성
//    @DisplayName("createSchedule: 작성 기능 확인")
//    @Test
//    public void createSchedule() throws Exception {
//
//        // given
//        final String url = "/api/schedules";
//        final String user = "user";
//        final String password = "password";
//        final String title = "title";
//        final String content = "content";
//        final LocalDateTime scheduleDate = LocalDateTime.of(2024, 6, 17, 22, 03);
//        final ScheduleCreateRequestDto requestDto = new ScheduleCreateRequestDto(user, password, title, content, scheduleDate);
//
//        final String requestBody = objectMapper.writeValueAsString(requestDto);
//
//        // when
//        ResultActions result = mockMvc.perform(post(url)
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .content(requestBody));
//
//        // then
//        result.andExpect(status().isCreated());
//
//        List<Schedule> schedule = scheduleRepository.findAll();
//        assertThat(schedule).hasSize(1);
//
//    }
//
//    //전체 일정 조회
//    @DisplayName("findAllchedule: 전체 일정 조회 확인")
//    @Test
//    public void findAllSchedule() throws Exception {
//
//        // given
//        final String url = "/api/schedules";
//        final String user = "user";
//        final String title = "title";
//        final String content = "content";
//
//        scheduleRepository.save(Schedule.builder()
//                .user(user)
//                .title(title)
//                .content(content)
//                .build());
//
//        // when
//       final ResultActions result = mockMvc.perform(get(url)
//               .accept(MediaType.APPLICATION_JSON));
//
//        // then
//        result.andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].user").value(user))
//                .andExpect(jsonPath("$[0].title").value(title))
//                .andExpect(jsonPath("$[0].content").value(content));
//    }
//
//    //선택 일정 조회
//    @DisplayName("findSchedule: 선택 일정 조회 확인")
//    @Test
//    public void findSchedule() throws Exception {
//
//        // given
//        final String url = "/api/schedules";
//        final String user = "user";
//        final String title = "title";
//        final String content = "content";
//
//        Schedule saveSchedule = scheduleRepository.save(Schedule.builder()
//                .user(user)
//                .title(title)
//                .content(content)
//                .build());
//
//
//        // when
//        final ResultActions result = mockMvc.perform(get(url,saveSchedule.
//                getId()));
//
//
//        // then
//        result.andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].user").value(user))
//                .andExpect(jsonPath("$[0].title").value(title))
//                .andExpect(jsonPath("$[0].content").value(content));
//    }
//
//    //수정
//    @DisplayName("updeteSchedule: 일정 수정 확인")
//    @Test
//    public void updateSchedule() throws Exception {
//
//        // given
//        final String url = "/api/schedules/{id}";
//        final String user = "user";
//        final String title = "title";
//        final String content = "content";
//        final String password = "password";
//
//        Schedule saveSchedule = scheduleRepository.save(Schedule.builder()
//                .user(user)
//                .title(title)
//                .content(content)
//                .password(password)
//                .build());
//
//        final String newTitle = "new title";
//        final String newContent = "new content";
//
//        ScheduleUpdateRequestDto request = new ScheduleUpdateRequestDto(newTitle, newContent);
//
//
//        // when
//        ResultActions result = mockMvc.perform(put(url, saveSchedule.getId())
//                .param("password", password)
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .content(objectMapper.writeValueAsString(request)));
//
//        // then
//        result.andExpect(status().isOk());
//
//        Schedule schedule = scheduleRepository.findById(saveSchedule.getId()).get();
//
//        assertThat(schedule.getTitle()).isEqualTo(newTitle);
//        assertThat(schedule.getContent()).isEqualTo(newContent);
//    }
//
//    //삭제
//    @DisplayName("deleteSchedule: 일정 삭제 확인")
//    @Test
//    public void deleteSchedule() throws Exception {
//
//        // given
//        final String url = "/api/schedules/{id}";
//        final String user = "user";
//        final String title = "title";
//        final String content = "content";
//        final String password = "password";
//
//        Schedule saveSchedule = scheduleRepository.save(Schedule.builder()
//                .user(user)
//                .title(title)
//                .content(content)
//                .password(password)
//                .build());
//
//
//        // when
//        mockMvc.perform(delete(url,saveSchedule.getId())
//                .param("pw", password))
//                .andExpect(status().isNoContent());
//
//        // then
//        List<Schedule> schedule = scheduleRepository.findAll();
//
//        assertThat(schedule).isEmpty();
//    }
//
//    }


