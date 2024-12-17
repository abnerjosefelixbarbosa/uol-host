package com.org.oul_host_back_end_java.repositories;

import org.springframework.stereotype.Repository;

import com.github.f4b6a3.ulid.UlidCreator;
import com.org.oul_host_back_end_java.entities.Player;
import com.org.oul_host_back_end_java.repositories.interfaces.IPlayerRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class PlayerRepository implements IPlayerRepository {
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public Player insertPlayer(Player player) {

		player.setId(UlidCreator.getUlid().toString());

		String sql = "INSERT INTO players (id, name, email, telephone, player_type) VALUES (:id, :name, :email, :telephone, :player_type)";

		entityManager.createNativeQuery(sql)
		             .setParameter("id", player.getId())
		             .setParameter("name", player.getName())
		             .setParameter("email", player.getEmail())
		             .setParameter("telephone", player.getTelephone())
		             .setParameter("player_type", player.getPlayerType())
		             .executeUpdate();

		return player;
	}
}