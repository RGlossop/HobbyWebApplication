package com.qa.persistence.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ChampionDTOTest {
	private String toString = "ChampionDTO [id=1L, name=Braum, role=Support]";
	
	@Test
	public void testToString() {
		ChampionDTO champ = new ChampionDTO(1L, "Braum", "Support");
		ChampionDTO champ2 = new ChampionDTO(1L, "Braum", "Support");
		assertEquals(champ.toString(), champ2.toString());
	}
	
}
