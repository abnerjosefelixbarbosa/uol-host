package com.org.oul_host_back_end_java.services.interfaces;

import com.org.oul_host_back_end_java.dtos.PlayerRequest;
import com.org.oul_host_back_end_java.dtos.PlayerResponse;

public interface IPlayerService {
	PlayerResponse registerPlayer(PlayerRequest request);
}