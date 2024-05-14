package com.meetup.teame.backend.domain.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meetup.teame.backend.domain.activity.entity.Activity;
import com.meetup.teame.backend.domain.personality.Personality;
import com.meetup.teame.backend.domain.experience.entity.Experience;
import com.meetup.teame.backend.domain.experience.entity.ExperienceType;
import com.meetup.teame.backend.domain.user.dto.response.ReadMainRes;
import com.meetup.teame.backend.domain.user.entity.Gender;
import com.meetup.teame.backend.domain.user.entity.User;
import com.meetup.teame.backend.domain.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void readMainPage() throws Exception {
        //given
//        User user = User.builder()
//                .id(1L)
//                .imageUrl("image url dummy")
//                .name("김또바")
//                .age(60L)
//                .gender(Gender.MALE)
//                .location("불광동")
//                .build();
//        given(userService.readMainPage()).willReturn(ReadMainRes.of(
//                List.of(Activity.of(
//                        "건강 선식 만들어 먹기",
//                        "서대문노인종합복지관",
//                        LocalDateTime.of(2024, 4, 20, 14, 0),
//                        11L,
//                        List.of(Personality.WINDLESS)
//                )),
//                List.of(Experience.of(
//                        ExperienceType.WORKOUT,
//                        "운동하실 분!!",
//                        user
//                )),
//                2500
//        ));
//
//        //when
//        ResultActions result = mockMvc.perform(get("/users/main"));
//
//        //then
//        verify(userService).readMainPage();
//        result
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.activities[0].title").value("건강 선식 만들어 먹기"))
//                .andExpect(jsonPath("$.activities[0].location").value("서대문노인종합복지관"))
//                .andExpect(jsonPath("$.experiences[0].name").value("김또바"))
//                .andExpect(jsonPath("$.experiences[0].location").value("불광동"))
//                .andExpect(jsonPath("$.experiences[0].message").value("운동하실 분!!"))
//                .andExpect(jsonPath("$.point").value(2500));
    }
}