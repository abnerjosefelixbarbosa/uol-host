package com.org.uol_host_back_end_spring_boot_java.models.repositories;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.github.f4b6a3.ulid.UlidCreator;
import com.org.uol_host_back_end_spring_boot_java.models.entities.Player;
import com.org.uol_host_back_end_spring_boot_java.models.mappers.interfaces.IPlayerMapper;
import com.org.uol_host_back_end_spring_boot_java.models.repositories.interfaces.IPlayerRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
public class PlayerRepository implements IPlayerRepository {
	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private IPlayerMapper playerMapper;

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
	public void deletePlayerById(String id) {
		String sql = "DELETE FROM players AS player WHERE player.id = :id";
		
		Query query = entityManager.createNativeQuery(sql);
		
		query.setParameter("id", id);
		
		query.executeUpdate();
	}

	public Page<Player> findAllPlayers(Pageable pageable) {
		String sql = "SELECT * FROM players";
		
		Query query = entityManager.createNativeQuery(sql);
		
		List<Player> players = (List<Player>) query
				.getResultList()
				.stream()
				.map(playerMapper::toPlayer)
				.collect(Collectors.toList());
		
		int start = (int) pageable.getOffset();
		
        int end = Math.min((start + pageable.getPageSize()), players.size());
		
        if (start > players.size()) {
            return new PageImpl<>(List.of(), pageable, players.size());
        }
        
        List<Player> subList = players.subList(start, end);
        
		return new PageImpl<Player>(subList, pageable, players.size());
	}

	@Transactional
	public void deleteAllPlayers() {
        String sql = "DELETE FROM players";
		
		Query query = entityManager.createNativeQuery(sql);
		
		query.executeUpdate();
	}

	public boolean existsPlayerById(String id) {
		String sql = "SELECT * FROM players AS player WHERE player.id = :id";	
	
	Query query = entityManager.createNativeQuery(sql, Object.class);
	
	query.setParameter("id", id);
	
	List<Object> objects = query.getResultList();
	
	if (!objects.isEmpty()) {
		return true;
	}
		
		return false;
	}
}