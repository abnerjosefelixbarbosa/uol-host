package com.org.oul_host_back_end_java.models.repositories.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.org.oul_host_back_end_java.models.entities.Player;

public interface IPlayerRepository {
	Player insertPlayer(Player player);
	
	boolean existsPlayerByNameOrEmail(Player player);
	
	void deleteAllPlayers();
	
	Page<Player> findAllPlayers(Pageable pageable);
}