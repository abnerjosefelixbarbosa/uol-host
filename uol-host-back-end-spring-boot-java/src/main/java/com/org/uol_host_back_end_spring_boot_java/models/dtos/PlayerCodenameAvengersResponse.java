package com.org.uol_host_back_end_spring_boot_java.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerCodenameAvengersResponse {
	@JsonProperty("codinome")
	private String codename;
}