package com.org.oul_host_back_end_java.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.oul_host_back_end_java.dtos.PlayerRequest;
import com.org.oul_host_back_end_java.dtos.PlayerResponse;
import com.org.oul_host_back_end_java.services.interfaces.IPlayerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/players")
public class PlayerController {
	@Autowired
	private IPlayerService playerService;
	
	
	@PostMapping("/register-player")
	public ResponseEntity<PlayerResponse> registerPlayer(@Valid @RequestBody PlayerRequest request) {
		PlayerResponse response = playerService.registerPlayer(request);
		
		return ResponseEntity.status(201).body(response);
	}
}