package com.org.oul_host_back_end_java.models.services.interfaces;

import java.util.List;

import com.org.oul_host_back_end_java.models.dtos.PlayerRequest;
import com.org.oul_host_back_end_java.models.dtos.PlayerResponse;

public interface IPlayerService {
	PlayerResponse registerPlayer(PlayerRequest request);
	
	List<PlayerResponse> listPlayer();
}