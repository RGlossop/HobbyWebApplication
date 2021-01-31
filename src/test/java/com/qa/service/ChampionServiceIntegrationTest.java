package com.qa.service;

import static org.assertj.core.api.Assertions.assertThat;

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
import com.qa.persistence.dto.ChampionDTO;
import com.qa.persistence.repos.ChampionRepo;

@SpringBootTest(classes = HobbyWebApplication.class)
public class ChampionServiceIntegrationTest {

	@Autowired
	private ChampionService service;
	
	@Autowired
	private ChampionRepo repo;
	
	private ChampionDomain testChamp;
	private ChampionDomain testChampWithID;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ObjectMapper jsonifier;
	private ChampionDTO mapToDTO(ChampionDomain champ) {
		return this.mapper.map(champ, ChampionDTO.class);
	}
	
	@BeforeEach
	public void init() {
		this.testChamp = new ChampionDomain();
		this.testChamp.setName("Braum");
		this.testChamp.setRole("Support");
		this.repo.deleteAll();
		
		this.testChampWithID = this.repo.save(this.testChamp);
	}
	
	@Test
	void testCreateChamp() throws Exception {
		assertThat(this.jsonifier.writeValueAsString(this.mapToDTO(this.testChampWithID)))
					.isEqualTo(this.jsonifier.writeValueAsString(this.service.create(testChamp)));
	}
	
	@Test
	public void testDelete() {
		assertThat(this.service.delete(this.testChampWithID.getId())).isTrue();
	}
	
	@Test
	public void testReadOne() throws Exception {
		assertThat(this.jsonifier.writeValueAsString(this.service.readOne(this.testChampWithID.getId())))
					.isEqualTo(this.jsonifier.writeValueAsString(this.mapToDTO(testChampWithID)));
	}
	
	@Test
	public void testReadAll() throws Exception {
		assertThat(this.jsonifier.writeValueAsString(this.service.readAll()))
				.isEqualTo(this.jsonifier.writeValueAsString(Stream.of(this.mapToDTO(testChampWithID)).collect(Collectors.toList())));
	}
	@Test
	public void testUpdate() throws Exception {
		ChampionDomain newChamp = new ChampionDomain();
		newChamp.setName("Braum");
		newChamp.setRole("Tank");
		ChampionDTO updateChamp = new ChampionDTO();
		updateChamp.setId(this.testChampWithID.getId());
		updateChamp.setName(newChamp.getName());
		updateChamp.setRole(newChamp.getRole());
		
		assertThat(this.jsonifier.writeValueAsString(this.service.update(this.testChampWithID.getId(), newChamp)))
					.isEqualTo(this.jsonifier.writeValueAsString(updateChamp));
	}
}
