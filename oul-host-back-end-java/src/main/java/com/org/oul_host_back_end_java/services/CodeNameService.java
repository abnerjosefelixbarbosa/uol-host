package com.org.oul_host_back_end_java.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.org.oul_host_back_end_java.entities.Player;
import com.org.oul_host_back_end_java.services.interfaces.ICodeNameService;

@Service
public class CodeNameService implements ICodeNameService {
	private RestTemplate restTemplate = new RestTemplate();
	
	public Player getCodeName(Player player) {	
		if (player.getPlayerType().getType() == "AVENGERS") {
			player = getAVENGERS(player.getPlayerType().getUri());
		} else {
			player = getJUSTICE_LEAGUE(player.getPlayerType().getUri());
		}
		
		return player;
	}
	
	private Player getAVENGERS(String uri) {
		ResponseEntity<Player[]> response = restTemplate.getForEntity(uri, .class);
		
		return response.getBody();
	}
	
    private Player getJUSTICE_LEAGUE(String uri) {
		
		return null;
	}
}