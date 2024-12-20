package com.org.oul_host_back_end_java.models.mappers.interfaces;

import com.org.oul_host_back_end_java.models.dtos.PlayerRequest;
import com.org.oul_host_back_end_java.models.dtos.PlayerResponse;
import com.org.oul_host_back_end_java.models.entities.Player;

public interface IPlayerMapper {
	Player toPlayer(PlayerRequest request);
	
	Player toPlayer(Object object);
	
	PlayerResponse toPlayerResponse(Player player);
}
