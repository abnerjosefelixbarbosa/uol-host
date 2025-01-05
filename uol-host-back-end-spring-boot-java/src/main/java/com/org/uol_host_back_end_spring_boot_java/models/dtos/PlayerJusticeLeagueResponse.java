package com.org.uol_host_back_end_spring_boot_java.models.dtos;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerJusticeLeagueResponse {
	@JacksonXmlProperty(localName = "codinomes")
	private List<PlayerCodenameJusticeLeagueResponse> codenames; 
}