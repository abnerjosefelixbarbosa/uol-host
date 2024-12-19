package com.org.oul_host_back_end_java.services;



import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.oul_host_back_end_java.dtos.PlayerRequest;
import com.org.oul_host_back_end_java.dtos.PlayerResponse;
import com.org.oul_host_back_end_java.entities.Player;
import com.org.oul_host_back_end_java.repositories.interfaces.IPlayerRepository;
import com.org.oul_host_back_end_java.services.interfaces.ICodenameService;
import com.org.oul_host_back_end_java.services.interfaces.IPlayerService;

@Service
public class PlayerService implements IPlayerService {
	@Autowired
	private IPlayerRepository playerRepository;
	@Autowired
	private ICodenameService codenameService;

	public PlayerResponse registerPlayer(PlayerRequest request) {
        Player player = new Player();
   
        BeanUtils.copyProperties(request, player);
        
        boolean isExists =  playerRepository.existsPlayerByCodename(player);
        
        if (isExists) {
        	throw new RuntimeException();
        }
        
        codenameService.getCodeName(player);
		
		player = playerRepository.insertPlayer(player);
		
		//playerRepository.selectPlayerByCodename(player);
	
		PlayerResponse response = new PlayerResponse();
		
		BeanUtils.copyProperties(player, response);
		
		return response;
	}
}
