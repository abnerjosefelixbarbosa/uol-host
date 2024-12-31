package com.org.uol_host_back_end_spring_boot_java.models.services.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.org.uol_host_back_end_spring_boot_java.models.dtos.PlayerRequest;
import com.org.uol_host_back_end_spring_boot_java.models.dtos.PlayerResponse;

public interface IPlayerService {
	PlayerResponse registerPlayer(PlayerRequest request);
	
	Page<PlayerResponse> listPlayers(Pageable pageable);
	
	void deletePlayerById(String id);
}