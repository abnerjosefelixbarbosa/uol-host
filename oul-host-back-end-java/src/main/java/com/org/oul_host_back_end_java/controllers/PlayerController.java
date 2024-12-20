package com.org.oul_host_back_end_java.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.oul_host_back_end_java.models.dtos.PlayerRequest;
import com.org.oul_host_back_end_java.models.dtos.PlayerResponse;
import com.org.oul_host_back_end_java.models.services.interfaces.IPlayerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/players")
public class PlayerController {
	@Autowired
	private IPlayerService playerService;
	
	
	@PostMapping("/register-player")
	public ResponseEntity<PlayerResponse> registerPlayer(@Valid @RequestBody PlayerRequest request) {
		PlayerResponse response = playerService.registerPlayer(request);
		
		//System.out.println(response.getPlayerType().toString());
		
		return ResponseEntity.status(201).body(response);
	}
	
	@GetMapping("/list-player")
	public ResponseEntity<List<PlayerResponse>> listPlayer() {
		List<PlayerResponse> responses = playerService.listPlayer();
		
		return ResponseEntity.status(201).body(responses);
	}
}