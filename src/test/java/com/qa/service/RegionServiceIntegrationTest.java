package com.qa.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.HobbyWebApplication;
import com.qa.persistence.domain.ChampionDomain;
import com.qa.persistence.domain.RegionDomain;
import com.qa.persistence.dto.RegionDTO;
import com.qa.persistence.repos.RegionRepo;

@SpringBootTest(classes = HobbyWebApplication.class)
public class RegionServiceIntegrationTest {

	@Autowired
	private RegionService service;

	@Autowired
	private RegionRepo repo;

	private RegionDomain testRegion;
	private RegionDomain testRegionWithID;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private ObjectMapper jsonifier;

	private RegionDTO mapToDTO(RegionDomain region) {
		return this.mapper.map(region, RegionDTO.class);
	}

	@BeforeEach
	public void init() {
		this.testRegion = new RegionDomain();
		this.testRegion.setName("Frelyord");
		this.testRegion.setDescription("Is Cold");

		this.repo.deleteAll();

		this.testRegionWithID = this.repo.save(this.testRegion);
	}

	@Test
	public void testCreateRegion() throws Exception {
		assertThat(this.jsonifier.writeValueAsString(this.mapToDTO(this.testRegionWithID)))
				.isEqualTo(this.jsonifier.writeValueAsString(this.service.create(testRegion)));
	}

	@Test
	public void testDelete() {
		assertThat(this.service.delete(this.testRegionWithID.getId())).isTrue();
	}

	@Test
	public void testReadOne() throws Exception {
		List<ChampionDomain> champList = new ArrayList<>();
		testRegion.setChampList(champList);
		assertThat(this.jsonifier.writeValueAsString(this.service.readOne(this.testRegionWithID.getId())))
				.isEqualTo(this.jsonifier.writeValueAsString(this.mapToDTO(testRegionWithID)));
	}
	
	@Test
	public void testReadAll() throws Exception {
		List<ChampionDomain> champList = new ArrayList<>();
		testRegion.setChampList(champList);
		assertThat(this.jsonifier.writeValueAsString(this.service.readAll()))
					.isEqualTo(this.jsonifier.writeValueAsString(Stream.of(this.mapToDTO(testRegionWithID)).collect(Collectors.toList())));
	}
	
	@Test
	public void testUpdate() {
		RegionDomain newRegion = new RegionDomain();
		newRegion.setName("Ionia");
		newRegion.setDescription("Remembers The Placidium");
	}
}
