package com.kbstar.littlestar.auth.service;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kbstar.littlestar.auth.dto.SignupRequest;
import com.kbstar.littlestar.pokemon.repository.PokemonRepository;
import com.kbstar.littlestar.user.repository.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Rollback
class AuthServiceTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private PokemonRepository pokemonRepository;

	@Autowired
	private UserRepository userRepository;

	@Test
	void 회원가입_성공() throws Exception {
		SignupRequest request = new SignupRequest();
		request.setUsername("테스트유저1");
		request.setPassword("1234pass!");
		request.setAge(9);
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
		SignupRequest request = new SignupRequest();
		request.setUsername("테스트유저2");
		request.setPassword("5678pass!");
		request.setAge(10);
		request.setMileage(0);
		request.setMainPokemonId(1L);

		mockMvc.perform(post("/api/auth/signup")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request)))
			.andExpect(status().isOk());

		Map<String, String> loginRequest = new HashMap<>();
		loginRequest.put("username", "테스트유저2");
		loginRequest.put("password", "5678pass!");

		mockMvc.perform(post("/api/auth/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(loginRequest)))
			.andExpect(status().isOk())
			.andDo(print());
	}
}