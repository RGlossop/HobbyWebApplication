package com.qa.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.HobbyWebApplication;
import com.qa.persistence.domain.ChampionDomain;
import com.qa.persistence.dto.ChampionDTO;
import com.qa.service.ChampionService;

@SpringBootTest(classes = HobbyWebApplication.class)
public class ChampionControllerUnitTest {

	@Autowired
	private ChampionController controller;
	
	@MockBean
	private ChampionService service;
	
	private List<ChampionDomain> champList;
	
	private ChampionDTO testChamp;
	
	private ChampionDomain testChampWithID;
	
	private ChampionDTO champDTO;
	
	private final long id = 1L;
	
	private ModelMapper mapper = new ModelMapper();
	
	@Autowired
	private ObjectMapper jsonifier;
	
	private ChampionDTO mapToDTO(ChampionDomain champ) {
		return this.mapper.map(champ, ChampionDTO.class);
	}
	
	@BeforeEach
	public void init() {
		this.champList = new ArrayList<>();
		ChampionDomain champ = new ChampionDomain();
		champ.setName("Braum");
		champ.setRole("Support");
		this.testChamp = mapToDTO(champ);
		
		this.testChampWithID = new ChampionDomain();
		this.testChampWithID.setId(id);
		this.testChampWithID.setName(champ.getName());
		this.testChampWithID.setRole(champ.getRole());
		
		champList.add(testChampWithID);
		this.champDTO = mapToDTO(testChampWithID);
	}
	
	@Test
	public void testCreateChamp() throws Exception{
		when(this.service.create(testChampWithID)).thenReturn(this.champDTO);
		
		assertThat(new ResponseEntity<ChampionDTO>(this.champDTO, HttpStatus.CREATED))
					.isEqualTo(this.controller.create(testChampWithID));
	
		verify(this.service, times(1)).create(this.testChampWithID);
	}
	
	@Test
	public void testDeleteChamp() throws Exception {
		this.controller.deleteChamp(id);
		
		verify(this.service, times(1)).delete(id);
	}
	
	@Test
	public void testReadOne() {
		when(this.service.readOne(this.id)).thenReturn(this.champDTO);
		
		assertThat(new ResponseEntity<ChampionDTO>(this.champDTO, HttpStatus.OK))
					.isEqualTo(this.controller.readChamp(this.id));
	
		verify(this.service, times(1)).readOne(this.id);
	}
	
	@Test
	public void testReadAll() {
		when(service.readAll()).thenReturn(this.champList.stream().map(this::mapToDTO).collect(Collectors.toList()));
		
		assertThat(this.controller.readAll().getBody().isEmpty()).isFalse();
		
		verify(service, times(1)).readAll();
	}
	
	@Test
	public void testUpdate() {
		ChampionDomain newChamp = new ChampionDomain();
		newChamp.setName("Braum");
		newChamp.setRole("Tank");
		ChampionDTO updateChamp = new ChampionDTO();
		updateChamp.setId(this.id);
		updateChamp.setName(newChamp.getName());
		updateChamp.setRole(newChamp.getRole());
		
		when(this.service.update(id, newChamp)).thenReturn(updateChamp);
		
		assertThat(new ResponseEntity<ChampionDTO>(updateChamp, HttpStatus.ACCEPTED))
					.isEqualTo(this.controller.update(this.id, newChamp));
		
		verify(this.service, times(1)).update(this.id, newChamp);
	}
}
