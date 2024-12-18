package com.org.oul_host_back_end_java.services;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.org.oul_host_back_end_java.dtos.PlayerAvengersResponse;
import com.org.oul_host_back_end_java.dtos.PlayerJusticeLeagueResponse;
import com.org.oul_host_back_end_java.entities.Player;
import com.org.oul_host_back_end_java.enums.PlayerType;
import com.org.oul_host_back_end_java.services.interfaces.ICodenameService;

@Service
public class CodenameService implements ICodenameService {
	public Player getCodeName(Player player) {	
		PlayerAvengersResponse playerAvengersResponse = getAvengers();
		
		PlayerJusticeLeagueResponse playerJusticeLeagueResponse = getJusticeLeague();
		
		//System.out.println(playerJusticeLeagueResponse);
			
		if (player.getPlayerType().getType() == "AVENGERS") {
			
			player.setCodename(playerAvengersResponse.getAvengers().stream().findFirst().get().getCodename());
			
		} else {
			
			player.setCodename(playerJusticeLeagueResponse.getCodenames().stream().findFirst().get().getCodename());
			
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