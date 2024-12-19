package com.org.oul_host_back_end_java.models.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.github.f4b6a3.ulid.UlidCreator;
import com.org.oul_host_back_end_java.models.entities.Player;
import com.org.oul_host_back_end_java.models.repositories.interfaces.IPlayerRepository;

import jakarta.persistence.EntityManager;
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

		Query query = entityManager.createNativeQuery(sql);
		
		query.setParameter("id", player.getId());
		
		query.setParameter("name", player.getName());
		
		query.setParameter("email", player.getEmail());
		
		query.setParameter("telephone", player.getTelephone());
		
		query.setParameter("player_type", player.getPlayerType());
		
		query.setParameter("codename", player.getCodename());
		
		query.executeUpdate();
		
		return player;
	}

	public boolean existsPlayerByNameOrEmail(Player player) {
		String sql = "SELECT * FROM players AS player " +
	                 "WHERE player.name = :name " +
	                 "OR player.email = :email";	
		
		Query query = entityManager.createNativeQuery(sql, Object.class);
		
		query.setParameter("name", player.getName());
		
		query.setParameter("email", player.getEmail());
		
		List<Object> objects = query.getResultList();
		
		if (!objects.isEmpty()) {
			return true;
		}
		
		return false;
	}

	@Transactional
	public void deleteAllPlayers() {
		String sql = "DELETE FROM players";
		
		Query query = entityManager.createNativeQuery(sql);
		
		query.executeUpdate();
	}

	@Override
	public List<Player> findAllPlayers() {
		//List<Player> players = new ArrayList<Player>();
		
		String sql = "SELECT * FROM players AS player";
		
		Query query = entityManager.createNativeQuery(sql, "Player");
		
		List<Player> players = query.getResultList();
		
		/*
		if (!objects.isEmpty()) {
			
			for (int i = 0; i < objects.size(); i++) {
				
				
				//array_type array_element = array[i];
			}
			
		}
		*/
		
		return players;
	}
}