package com.org.uol_host_back_end_spring_boot_java.models.mappers.interfaces;

import com.org.uol_host_back_end_spring_boot_java.models.dtos.PlayerRequest;
import com.org.uol_host_back_end_spring_boot_java.models.dtos.PlayerResponse;
import com.org.uol_host_back_end_spring_boot_java.models.entities.Player;

public interface IPlayerMapper {
	Player toPlayer(PlayerRequest request);
	
	Player toPlayer(Object object);
	
	PlayerResponse toPlayerResponse(Player player);
}
