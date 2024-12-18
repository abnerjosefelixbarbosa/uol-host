package com.org.oul_host_back_end_java.dtos;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerCodenameJusticeLeagueResponse {
	@JacksonXmlProperty(localName = "codinome")
	private String codename;
}