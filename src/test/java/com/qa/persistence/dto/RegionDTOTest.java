package com.qa.persistence.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class RegionDTOTest {

	@Test
	public void testToString() {
		List<ChampionDTO> champList = new ArrayList<>();
		
		RegionDTO region = new RegionDTO(1L, "Frelyord", "Is Cold", champList);
		RegionDTO region2 = new RegionDTO(1L, "Frelyord", "Is Cold", champList);
		
		assertEquals(region.toString(), region2.toString());
	}
}
