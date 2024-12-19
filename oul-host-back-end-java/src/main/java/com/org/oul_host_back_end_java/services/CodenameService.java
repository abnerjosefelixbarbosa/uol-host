package com.org.oul_host_back_end_java.services;

import java.util.ArrayList;
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
import com.org.oul_host_back_end_java.services.interfaces.ICodenameService;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;

@Service
public class CodenameService implements ICodenameService {
	@Autowired
	private ObjectMapper objectMapper;
	private List<PlayerCodenameAvengersResponse> avengersList = new ArrayList<PlayerCodenameAvengersResponse>();
	private List<PlayerCodenameJusticeLeagueResponse> justiceLeagueList = new ArrayList<PlayerCodenameJusticeLeagueResponse>();;
	
	public Player getCodeName(Player player) {	
		
			
		if (player.getPlayerType().getType() == "AVENGERS") {
			PlayerCodenameAvengersResponse codename = avengersList.parallelStream().findFirst()
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
	private void loadAvengers() {	
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
			
			PlayerAvengersResponse playerAvengersResponse = objectMapper.readValue(response, PlayerAvengersResponse.class);
			
			avengersList = playerAvengersResponse.getAvengers();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	@PostConstruct
    private void loadJusticeLeague() {
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
    		
    		justiceLeagueList = playerJusticeLeagueResponse.getCodenames();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}