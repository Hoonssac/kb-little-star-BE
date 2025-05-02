package com.kbstar.littlestar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kbstar.littlestar.dto.SignupRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void 회원가입_정상작동() throws Exception {
        SignupRequest request = new SignupRequest();
        request.setUsername("testUser123");
        request.setPassword("secure1234");
        request.setAge(10);
        request.setAge(10);
        request.setPokemonIds(List.of(0));
        request.setMainPokemonId(1);
        request.setQuestionIds(List.of());
        request.setMileage(0);
        request.setTicketCount(0);

        mockMvc.perform(post("/api/auth/signup") // POST 요청
                        .contentType(MediaType.APPLICATION_JSON) // JSON 타입 지정
                        .content(objectMapper.writeValueAsString(request))) // Java 객체 -> JSON 변환
                .andExpect(status().isOk()); // 기대하는 응답 코드: 200 OK
    }

    @Test
    void 로그인_성공() throws Exception {
        // 회원가입 선행
        SignupRequest request = new SignupRequest();
        request.setUsername("loginTestUser");
        request.setPassword("loginPass123");
        request.setAge(11);
        request.setPokemonIds(List.of(1));
        request.setMainPokemonId(1);
        request.setQuestionIds(List.of());
        request.setMileage(0);
        request.setTicketCount(0);

        mockMvc.perform(post("/api/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        // 로그인 요청
        Map<String, String> loginRequest = Map.of(
                "username", "loginTestUser",
                "password", "loginPass123"
        );

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk());
    }
}
