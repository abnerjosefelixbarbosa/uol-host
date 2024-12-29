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
	@NotEmpty(message = "nome vazio")
	@NotNull(message = "nome nulo")
	private String name;
	@NotEmpty(message = "email vazio")
	@NotNull(message = "email nulo")
	@Email(message = "email invalido")
	private String email;
	@NotNull(message = "telefone nulo")
	private String telephone;
	@NotNull(message = "player nulo")
	@Enumerated(EnumType.STRING)
	private PlayerType playerType;
}