package com.org.uol_host_back_end_spring_boot_java.models.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.org.uol_host_back_end_spring_boot_java.models.dtos.PlayerRequest;
import com.org.uol_host_back_end_spring_boot_java.models.dtos.PlayerResponse;
import com.org.uol_host_back_end_spring_boot_java.models.entities.Player;
import com.org.uol_host_back_end_spring_boot_java.models.mappers.PlayerMapper;
import com.org.uol_host_back_end_spring_boot_java.models.repositories.interfaces.IPlayerRepository;
import com.org.uol_host_back_end_spring_boot_java.models.services.interfaces.ICodenameService;
import com.org.uol_host_back_end_spring_boot_java.models.services.interfaces.IPlayerService;
import com.org.uol_host_back_end_spring_boot_java.models.validations.interfaces.IPlayerValidation;

@Service
public class PlayerService implements IPlayerService {
	@Autowired
	private IPlayerRepository playerRepository;
	@Autowired
	private ICodenameService codenameService;
	@Autowired
	private PlayerMapper playerMapper;
	@Autowired
	private IPlayerValidation playerValidation; 

	public PlayerResponse registerPlayer(PlayerRequest request) {
        Player player = new Player();
        
        player = playerMapper.toPlayer(request);
        
        playerValidation.playerValidation(player);
        
        codenameService.getCodeName(player);
		
		player = playerRepository.insertPlayer(player);
		
		return playerMapper.toPlayerResponse(player);
	}

	public Page<PlayerResponse> listPlayers(Pageable pageable) {
		return playerRepository
				.findAllPlayers(pageable)
				.map(playerMapper::toPlayerResponse);
	}

	public void deletePlayers() {
		playerRepository.deleteAllPlayers();
	}
}
