package com.org.oul_host_back_end_java.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import com.github.f4b6a3.ulid.UlidCreator;
import com.org.uol_host_back_end_java.models.dtos.PlayerRequest;
import com.org.uol_host_back_end_java.models.entities.Player;
import com.org.uol_host_back_end_java.models.enums.PlayerType;
import com.org.uol_host_back_end_java.models.repositories.interfaces.IPlayerRepository;

@SpringBootTest
@ActiveProfiles("dev")
@AutoConfigureMockMvc
class PlayerControllerIT {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private IPlayerRepository playerRepository;

	@BeforeEach
	void setUp() throws Exception {
		playerRepository.deleteAllPlayers();
	}

	@AfterEach
	void tearDown() throws Exception {
		playerRepository.deleteAllPlayers();
	}

	@Test
	void registerPlayer() throws Exception {
		loadPlayer();
		
		PlayerRequest request = new PlayerRequest();
		request.setName("name2");
		request.setEmail("email3@gmail.com");
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
	
	@Test
	void listPlayer() throws Exception {
		loadPlayer();

		mockMvc.perform(get("/api/players/list-player")
				.queryParam("page", "0")
				.queryParam("size", "2")
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(print());
	}
	
	void loadPlayer() throws Exception  {
		Player player1 = new Player();
		player1.setId(UlidCreator.getUlid().toString());
		player1.setCodename("codename1");
		player1.setName("name1");
		player1.setEmail("email1@gmail.com");
		player1.setPlayerType(PlayerType.AVENGERS);
		player1.setTelephone("");
		
		playerRepository.insertPlayer(player1);
		
		Player player2 = new Player();
		player2.setId(UlidCreator.getUlid().toString());
		player2.setCodename("codename2");
		player2.setName("name2");
		player2.setEmail("email2@gmail.com");
		player2.setPlayerType(PlayerType.AVENGERS);
		player2.setTelephone("");
		
		playerRepository.insertPlayer(player2);
	}
}
