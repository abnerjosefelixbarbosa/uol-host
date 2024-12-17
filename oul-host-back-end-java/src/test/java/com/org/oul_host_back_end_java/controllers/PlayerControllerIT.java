package com.org.oul_host_back_end_java.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.oul_host_back_end_java.dtos.PlayerRequest;
import com.org.oul_host_back_end_java.enums.PlayerType;

@SpringBootTest
@ActiveProfiles("dev")
@AutoConfigureMockMvc
class PlayerControllerIT {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void registerPlayer() throws Exception {
		PlayerRequest request = new PlayerRequest();
		request.setName("name1");
		request.setEmail("email1@gmail.com");
		request.setPlayerType(PlayerType.AVENGERS);
		request.setTelephone("");
		
		String obj = objectMapper.writeValueAsString(request);

		mockMvc.perform(post("/api/players/register-player")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(obj))
		.andExpect(MockMvcResultMatchers.status().isCreated())
				.andDo(print());
	}
}
