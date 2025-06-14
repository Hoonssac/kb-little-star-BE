package com.kbstar.littlestar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kbstar.littlestar.auth.dto.SignupRequest;
import com.kbstar.littlestar.pokemon.domain.Pokemon;
import com.kbstar.littlestar.pokemon.repository.PokemonRepository;
import com.kbstar.littlestar.user.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PokemonRepository pokemonRepository;

	@Autowired
	private UserRepository userRepository;

    @BeforeEach
    void setup() {
        pokemonRepository.save(Pokemon.builder()
            .id(1L)
            .name("피카츄")
            .imageUrl("https://example.com/pikachu.png")
            .height(0.4)
            .weight(6.0)
            .types("전기")
            .build());
    }

    @Test
    void 회원가입_성공() throws Exception {

        SignupRequest request = new SignupRequest();
        request.setUsername("피카츄12345");
        request.setPassword("pikachu12345");
        request.setAge(10);
        request.setMileage(0);
        request.setMainPokemonId(1L);

        mockMvc.perform(post("/api/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    void 로그인_성공() throws Exception {
        Map<String, String> loginRequest = new HashMap<>();
        loginRequest.put("username", "피카츄12345");
        loginRequest.put("password", "pikachu12345");

        mockMvc.perform(post("/api/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(loginRequest)))
            .andExpect(status().isOk())
            .andDo(print());
    }
}
