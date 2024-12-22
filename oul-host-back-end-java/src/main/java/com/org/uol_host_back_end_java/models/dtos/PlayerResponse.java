package com.org.uol_host_back_end_java.models.dtos;

import com.org.uol_host_back_end_java.models.enums.PlayerType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerResponse {
	private String id;
	private String name;
	private String email;
	private String telephone;
	private PlayerType playerType;
	private String codename;
}