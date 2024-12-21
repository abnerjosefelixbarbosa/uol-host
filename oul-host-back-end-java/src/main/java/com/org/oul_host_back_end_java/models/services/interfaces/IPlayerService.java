package com.org.oul_host_back_end_java.models.services.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.org.oul_host_back_end_java.models.dtos.PlayerRequest;
import com.org.oul_host_back_end_java.models.dtos.PlayerResponse;

public interface IPlayerService {
	PlayerResponse registerPlayer(PlayerRequest request);
	
	Page<PlayerResponse> listPlayer(Pageable pageable);
}