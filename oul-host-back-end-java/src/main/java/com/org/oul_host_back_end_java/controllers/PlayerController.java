package com.org.oul_host_back_end_java.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
		return ResponseEntity
				.status(201)
				.body(playerService.registerPlayer(request));
	}
	
	@GetMapping("/list-player")
	public ResponseEntity<Page<PlayerResponse>> listPlayer(Pageable pageable) {	
		return ResponseEntity
				.status(200)
				.body(playerService.listPlayer(pageable));
	}
}