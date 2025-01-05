package com.org.uol_host_back_end_spring_boot_java.models.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.org.uol_host_back_end_spring_boot_java.models.entities.Player;
import com.org.uol_host_back_end_spring_boot_java.models.repositories.interfaces.IPlayerRepository;
import com.org.uol_host_back_end_spring_boot_java.models.validations.interfaces.IPlayerValidation;

@Component
public class PlayerValidation implements IPlayerValidation {
	@Autowired
	private IPlayerRepository playerRepository;
	
	public void playerValidation(Player player) {
        boolean isExists = playerRepository.existsPlayerByNameOrEmail(player);
        
        if (isExists) {
        	throw new RuntimeException("jogador existe");
        }
	}
	
	public void playerValidation(String id) {
        boolean isExists = playerRepository.existsPlayerById(id);
        
        if (!isExists) {
        	throw new RuntimeException("jogador não existe");
        }
	}
}