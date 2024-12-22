package com.org.uol_host_back_end_spring_boot_java.models.services.interfaces;

import com.org.uol_host_back_end_spring_boot_java.models.entities.Player;

public interface ICodenameService {
	Player getCodeName(Player player);
}