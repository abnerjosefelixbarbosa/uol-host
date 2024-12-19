package com.org.oul_host_back_end_java.repositories.interfaces;

import com.org.oul_host_back_end_java.entities.Player;

public interface IPlayerRepository {
	Player insertPlayer(Player player);
	boolean existsPlayerByCodename(Player player);
}