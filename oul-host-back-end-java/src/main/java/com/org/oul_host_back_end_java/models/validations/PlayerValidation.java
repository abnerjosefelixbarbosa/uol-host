package com.org.oul_host_back_end_java.models.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.org.oul_host_back_end_java.models.entities.Player;
import com.org.oul_host_back_end_java.models.repositories.interfaces.IPlayerRepository;
import com.org.oul_host_back_end_java.models.validations.interfaces.IPlayerValidation;

@Component
public class PlayerValidation implements IPlayerValidation {
	@Autowired
	private IPlayerRepository playerRepository;
	
	public void playerValidation(Player player) {
        boolean isExists = playerRepository.existsPlayerByNameOrEmail(player);
        
        if (isExists) {
        	throw new RuntimeException("player exists");
        }
	}
}