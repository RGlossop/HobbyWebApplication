package com.qa.persistence.domain;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ChampDomainTests {

	private ChampionDomain champ;
	
	@Test
	public void testConstructor() {
		RegionDomain region = new RegionDomain();
		champ = new ChampionDomain(1L, "Dave", "Davidson", region);
		ChampionDomain equalsChamp = new ChampionDomain();
		equalsChamp.setId(champ.getId());
		equalsChamp.setName(champ.getName());
		equalsChamp.setRole(champ.getRole());
		equalsChamp.setRegion(champ.getRegion());
		
		assertEquals(champ.toString(), equalsChamp.toString());
	}
	
	@Test
	public void testEquals() {
		RegionDomain region = new RegionDomain();
		champ = new ChampionDomain(1L, "Dave", "Davidson", region);
		ChampionDomain equalsChamp = champ;
		
		assertTrue(champ.equals(equalsChamp));
		
	}
}
