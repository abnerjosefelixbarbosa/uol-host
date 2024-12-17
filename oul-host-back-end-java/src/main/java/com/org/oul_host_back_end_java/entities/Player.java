package com.org.oul_host_back_end_java.entities;

import com.org.oul_host_back_end_java.enums.PlayerType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
	private String id;
	private String name;
	private String email;
	private String telephone;
	private PlayerType playerType;
}