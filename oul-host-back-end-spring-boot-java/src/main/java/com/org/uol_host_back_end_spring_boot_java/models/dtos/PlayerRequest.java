package com.org.uol_host_back_end_spring_boot_java.models.dtos;

import com.org.uol_host_back_end_spring_boot_java.models.enums.PlayerType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerRequest {
	@NotEmpty(message = "name empty")
	@NotNull(message = "name null")
	private String name;
	@NotEmpty(message = "email empty")
	@NotNull(message = "email null")
	@Email(message = "email invalid")
	private String email;
	@NotNull(message = "telephone null")
	private String telephone;
	@NotNull(message = "player null")
	@Enumerated(EnumType.STRING)
	private PlayerType playerType;
}