package com.qa.persistence.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class RegionDomainTests {

	private RegionDomain region;
	
	@Test
	public void testConstructor() {
		List<ChampionDomain> champList = new ArrayList<>();
		region = new RegionDomain(1L, "Dave", "Davidson", champList);
		
		RegionDomain equalsRegion = new RegionDomain();
		equalsRegion.setId(region.getId());
		equalsRegion.setName(region.getName());
		equalsRegion.setDescription(region.getDescription());
		equalsRegion.setChampList(region.getChampList());
		
		assertEquals(region.toString(), equalsRegion.toString());
		
	}
	
	@Test
	public void testEquals() {
		List<ChampionDomain> champList = new ArrayList<>();
		region = new RegionDomain(1L, "Dave", "Davidson", champList);
		
		RegionDomain equalsRegion = region;
		
		assertTrue(region.equals(equalsRegion));
	}
}
