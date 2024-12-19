package com.org.oul_host_back_end_java.repositories;

import org.springframework.stereotype.Repository;

import com.github.f4b6a3.ulid.UlidCreator;
import com.org.oul_host_back_end_java.entities.Player;
import com.org.oul_host_back_end_java.repositories.interfaces.IPlayerRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
public class PlayerRepository implements IPlayerRepository {
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public Player insertPlayer(Player player) {
		player.setId(UlidCreator.getUlid().toString());

		String sql = "INSERT INTO players (id, name, email, telephone, player_type, codename) " +
		             "VALUES (:id, :name, :email, :telephone, :player_type, :codename)";

		entityManager.createNativeQuery(sql)
		             .setParameter("id", player.getId())
		             .setParameter("name", player.getName())
		             .setParameter("email", player.getEmail())
		             .setParameter("telephone", player.getTelephone())
		             .setParameter("player_type", player.getPlayerType())
		             .setParameter("codename", player.getCodename())
		             .executeUpdate();

		return player;
	}

	public Player selectPlayerByCodename(Player player) {
		String sql = "SELECT * FROM players AS player " +
	                 "WHERE player.codename = :codename";
		
		Query query = entityManager.createNativeQuery(sql, Object.class);
		
		query.setParameter("codename", player.getCodename());
		
		Object[] objects = (Object[]) query.getSingleResult();
		
		if (objects != null) {
			player.setId(objects[0].toString());
			
			player.setName(objects[1].toString());
			
			player.setEmail(objects[2].toString());
			
			player.setTelephone(objects[3].toString());
			
		    player.setPlayerType(player.getPlayerType());
		    
		    player.setCodename(objects[5].toString());
		} else {
			return null;
		}
		
		return player;
	}
}