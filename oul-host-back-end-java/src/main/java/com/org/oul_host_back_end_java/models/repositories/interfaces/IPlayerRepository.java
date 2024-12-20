package com.org.oul_host_back_end_java.models.repositories.interfaces;

import java.util.List;

import com.org.oul_host_back_end_java.models.entities.Player;

public interface IPlayerRepository {
	Player insertPlayer(Player player);
	
	boolean existsPlayerByNameOrEmail(Player player);
	
	void deleteAllPlayers();
	
	List<Player> findAllPlayers();
}