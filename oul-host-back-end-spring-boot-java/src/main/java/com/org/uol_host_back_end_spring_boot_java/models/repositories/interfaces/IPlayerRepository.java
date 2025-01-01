package com.org.uol_host_back_end_spring_boot_java.models.repositories.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.org.uol_host_back_end_spring_boot_java.models.entities.Player;

public interface IPlayerRepository {
	Player insertPlayer(Player player);
	
	boolean existsPlayerByNameOrEmail(Player player);
	
	boolean existsPlayerById(String id);
	
	void deletePlayerById(String id);
	
	void deleteAllPlayers();
	
	Page<Player> findAllPlayers(Pageable pageable);
	
	Player editPlayer(String id, Player player);
}