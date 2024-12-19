package com.org.oul_host_back_end_java.services;

import java.util.List;

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
import com.org.oul_host_back_end_java.services.interfaces.ICodenameService;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;

@Service
public class CodenameService implements ICodenameService {
	public Player getCodeName(Player player) {	
		PlayerAvengersResponse avengersResponse = getAvengers();
		
		PlayerJusticeLeagueResponse justiceLeagueResponse = getJusticeLeague();
		
		List<PlayerCodenameAvengersResponse> avengersList = avengersResponse
				.getAvengers();
		
		List<PlayerCodenameJusticeLeagueResponse> justiceLeagueList = justiceLeagueResponse
				.getCodenames();
			
		if (player.getPlayerType().getType() == "AVENGERS") {
			PlayerCodenameAvengersResponse codename = avengersList.stream().findFirst()
					.orElseThrow(() -> new EntityNotFoundException("codename not found"));
			
			avengersList.remove(codename);
			
			player.setCodename(codename.getCodename());
		} else {
			PlayerCodenameJusticeLeagueResponse codename = justiceLeagueList.stream().findFirst()
					.orElseThrow(() -> new EntityNotFoundException("codename not found"));
			
			justiceLeagueList.remove(codename);
			
			player.setCodename(justiceLeagueList.stream().findFirst().get().getCodename());
		}
		
		return player;
	}
	
	@PostConstruct
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
	
	@PostConstruct
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