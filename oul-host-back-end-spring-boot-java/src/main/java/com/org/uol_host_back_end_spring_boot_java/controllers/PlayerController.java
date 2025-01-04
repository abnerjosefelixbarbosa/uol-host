package com.org.uol_host_back_end_spring_boot_java.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.org.uol_host_back_end_spring_boot_java.models.dtos.PlayerRequest;
import com.org.uol_host_back_end_spring_boot_java.models.dtos.PlayerResponse;
import com.org.uol_host_back_end_spring_boot_java.models.services.interfaces.IPlayerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/players")
public class PlayerController {
	@Autowired
	private IPlayerService playerService;
	
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "cria um jogador"),
			@ApiResponse(responseCode = "400", description = "retorna um erro de requesição")
	})
	@ResponseStatus(value = HttpStatus.CREATED)
	@Operation(summary = "registrar jogador", description = "registra um jogador")
	@PostMapping(value = "/register-player", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PlayerResponse> registerPlayer(@Valid @RequestBody PlayerRequest request) {
		PlayerResponse response = playerService.registerPlayer(request);
		
		return ResponseEntity.status(201).body(response);
	}
	
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "retorna um jogador"),
			@ApiResponse(responseCode = "400", description = "retorna um erro de requesição"),
	})
	@ResponseStatus(value = HttpStatus.OK)
	@Operation(summary = "editar jogador", description = "registra um jogador")
	@PutMapping(value = "/edit-player", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PlayerResponse> editPlayer(@RequestParam String id, @Valid @RequestBody PlayerRequest request) {
		PlayerResponse response = playerService.editPlayer(id, request);
		
		return ResponseEntity.status(200).body(response);
	}
	
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "retorna uma pagina de jogador"),
			@ApiResponse(responseCode = "400", description = "retorna um erro de requesição"),
	})
	@ResponseStatus(value = HttpStatus.OK)
	@Operation(summary = "listar jogador", description = "lista todos os jogadores")
	@GetMapping(value = "/list-player", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<PlayerResponse>> listPlayers(Pageable pageable) {	
		Page<PlayerResponse> page = playerService.listPlayers(pageable);
		
		return ResponseEntity.status(200).body(page);
	}
	
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "não retorna nem um conteudo"),
			@ApiResponse(responseCode = "400", description = "retorna um erro de requesição"),
	})
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@Operation(summary = "deletar jogador", description = "deleta um jogador pelo id")
	@DeleteMapping(value = "/delete-player-by-id", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deletePlayerById(@RequestParam String id) {	
		playerService.deletePlayerById(id);
		
		return ResponseEntity.status(204).body(null);
	}
}