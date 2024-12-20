package com.org.oul_host_back_end_java.models.services;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.oul_host_back_end_java.models.dtos.PlayerRequest;
import com.org.oul_host_back_end_java.models.dtos.PlayerResponse;
import com.org.oul_host_back_end_java.models.entities.Player;
import com.org.oul_host_back_end_java.models.mappers.PlayerMapper;
import com.org.oul_host_back_end_java.models.repositories.interfaces.IPlayerRepository;
import com.org.oul_host_back_end_java.models.services.interfaces.ICodenameService;
import com.org.oul_host_back_end_java.models.services.interfaces.IPlayerService;

@Service
public class PlayerService implements IPlayerService {
	@Autowired
	private IPlayerRepository playerRepository;
	@Autowired
	private ICodenameService codenameService;
	@Autowired
	private PlayerMapper playerMapper;

	public PlayerResponse registerPlayer(PlayerRequest request) {
        Player player = new Player();
        
        player = playerMapper.toPlayer(request);
        
        boolean isExists = playerRepository.existsPlayerByNameOrEmail(player);
        
        if (isExists) {
        	throw new RuntimeException("player exists");
        }
        
        codenameService.getCodeName(player);
		
		player = playerRepository.insertPlayer(player);
	
		PlayerResponse response = playerMapper.toPlayerResponse(player);
		
		return response;
	}

	public List<PlayerResponse> listPlayer() {
		return playerRepository
				.findAllPlayers()
				.stream()
				.map(playerMapper::toPlayerResponse)
				.toList();
	}
}
