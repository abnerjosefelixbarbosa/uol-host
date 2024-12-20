package com.org.oul_host_back_end_java.models.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.org.oul_host_back_end_java.models.dtos.PlayerRequest;
import com.org.oul_host_back_end_java.models.dtos.PlayerResponse;
import com.org.oul_host_back_end_java.models.entities.Player;
import com.org.oul_host_back_end_java.models.enums.PlayerType;
import com.org.oul_host_back_end_java.models.mappers.interfaces.IPlayerMapper;

@Component
public class PlayerMapper implements IPlayerMapper {

	public Player toPlayer(PlayerRequest request) {
		Player player = new Player();
		
		BeanUtils.copyProperties(request, player);
		
		return player;
	}
	
	public Player toPlayer(Object object) {
		Player player = new Player();
		Object[] objects = (Object[]) object;
		
		player.setId((String) objects[0]);
		player.setName((String) objects[1]);
		player.setEmail((String) objects[2]);
		player.setTelephone((String) objects[3]);
	
		int value = ((Number) objects[4]).intValue();
		
		if (value == 0) {
			player.setPlayerType(PlayerType.AVENGERS);
		} else {
			player.setPlayerType(PlayerType.JUSTICE_LEAGUE);
		}
		
		player.setCodename((String) objects[5]);;
		
		return player;
	}
	
	public PlayerResponse toPlayerResponse(Player player) {
		PlayerResponse response = new PlayerResponse();
		
		BeanUtils.copyProperties(player, response);
		
		return response;
	}
}