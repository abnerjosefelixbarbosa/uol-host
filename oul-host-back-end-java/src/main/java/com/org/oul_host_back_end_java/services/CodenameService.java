package com.org.oul_host_back_end_java.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.org.oul_host_back_end_java.dtos.PlayerAvengersResponse;
import com.org.oul_host_back_end_java.dtos.PlayerCodenameAvengersResponse;
import com.org.oul_host_back_end_java.dtos.PlayerCodenameJusticeLeagueResponse;
import com.org.oul_host_back_end_java.dtos.PlayerJusticeLeagueResponse;
import com.org.oul_host_back_end_java.entities.Player;
import com.org.oul_host_back_end_java.enums.PlayerType;
import com.org.oul_host_back_end_java.repositories.interfaces.IPlayerRepository;
import com.org.oul_host_back_end_java.services.interfaces.ICodenameService;

@Service
public class CodenameService implements ICodenameService {
	@Autowired
	private IPlayerRepository playerRepository;
	
	public Player getCodeName(Player player) {	
		PlayerAvengersResponse avengersResponse = getAvengers();
		
		PlayerJusticeLeagueResponse justiceLeagueResponse = getJusticeLeague();
		
		List<PlayerCodenameAvengersResponse> avengersList = avengersResponse
				.getAvengers();
		
		List<PlayerCodenameJusticeLeagueResponse> justiceLeagueList = justiceLeagueResponse
				.getCodenames();
		
		//System.out.println(playerJusticeLeagueResponse);
			
		if (player.getPlayerType().getType() == "AVENGERS") {
		
			/*
			for (PlayerCodenameAvengersResponse i : avengersList) {
				if (i.getCodename() != player.getCodename() || player.getCodename().isEmpty() || player.getCodename() == null) {
					player.setCodename(avengersList.stream().findFirst().get().getCodename());
				}
			}
			*/
			
			avengersList
			.stream()
			.forEach((i) -> {

				Player selectPlayerByCodename = playerRepository.selectPlayerByCodename(player);
				
				if (selectPlayerByCodename == null) {
					
				}
				
				//if (a.getCodename())
			});
	
			player.setCodename(avengersList.stream().findFirst().get().getCodename());
		} else {
			
			
			
			player.setCodename(justiceLeagueList.stream().findFirst().get().getCodename());
		}
		
		return player;
	}
	
	private PlayerAvengersResponse getAvengers() {	
		try {
			WebClient webClient = WebClient.builder()
					.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.defaultHeader(HttpHeaders.ACCEPT, MediaType.TEXT_PLAIN_VALUE)
					.baseUrl(PlayerType.AVENGERS.getUri())
					.build();
			
			String response = webClient.get()
					.retrieve()
			        .bodyToMono(String.class)
			        .block();
			
			ObjectMapper objectMapper = new ObjectMapper();
			
			PlayerAvengersResponse playerAvengersResponse = objectMapper.readValue(response, PlayerAvengersResponse.class);
			
			return playerAvengersResponse;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
    private PlayerJusticeLeagueResponse getJusticeLeague() {
    	
    	try {
    		WebClient webClient = WebClient.builder()
    				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML_VALUE)
    				.defaultHeader(HttpHeaders.ACCEPT, MediaType.TEXT_PLAIN_VALUE)
    				.baseUrl(PlayerType.JUSTICE_LEAGUE.getUri())
    				.build();
    		
    		String response = webClient.get()
					.retrieve()
			        .bodyToMono(String.class)
			        .block();
    		
    		XmlMapper xmlMapper = new XmlMapper();
    		
    		PlayerJusticeLeagueResponse playerJusticeLeagueResponse = xmlMapper.readValue(response, PlayerJusticeLeagueResponse.class);
    		
    		return playerJusticeLeagueResponse;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}