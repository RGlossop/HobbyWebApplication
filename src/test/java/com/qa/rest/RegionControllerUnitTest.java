package com.qa.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.HobbyWebApplication;
import com.qa.persistence.domain.RegionDomain;
import com.qa.persistence.dto.RegionDTO;
import com.qa.service.RegionService;

@SpringBootTest(classes = HobbyWebApplication.class)
public class RegionControllerUnitTest {

	@Autowired
	private RegionController controller;
	
	@MockBean
	private RegionService service;
	
	private List<RegionDomain> regionList;
	
	private RegionDTO testRegion;
	
	private RegionDomain testRegionWithID;
	
	private RegionDTO regionDTO;
	
	private final long id = 1L;
	
	private ModelMapper mapper = new ModelMapper();
	
	@Autowired
	private ObjectMapper jsonifier;
	
	private RegionDTO mapToDTO(RegionDomain region) {
		return this.mapper.map(region, RegionDTO.class);
	}
	@BeforeEach
	public void init() {
		this.regionList = new ArrayList();
		RegionDomain region = new RegionDomain();
		region.setName("Frelyord");
		region.setDescription("Is Cold");
		this.testRegion = mapToDTO(region);
		
		this.testRegionWithID = new RegionDomain();
		this.testRegionWithID.setId(id);
		this.testRegionWithID.setName(region.getName());
		this.testRegionWithID.setDescription(region.getDescription());
		
		regionList.add(testRegionWithID);
		this.regionDTO = mapToDTO(testRegionWithID);
	}
	
	@Test
	public void testCreate() {
		when(this.service.create(testRegionWithID)).thenReturn(this.regionDTO);
		
		assertThat(new ResponseEntity<RegionDTO>(this.regionDTO, HttpStatus.CREATED))
					.isEqualTo(this.controller.create(this.testRegionWithID));
	}
	
	@Test
	public void testDelete() {
		this.controller.delete(id);
		
		verify(this.service, times(1)).delete(id);
	}
	
	@Test
	public void testReadOne() {
		when(this.service.readOne(this.id)).thenReturn(this.regionDTO);
		
		assertThat(new ResponseEntity<RegionDTO>(this.regionDTO, HttpStatus.OK))
					.isEqualTo(this.controller.read(this.id));
	}
	
	@Test
	public void testReadAll() {
		when(service.readAll()).thenReturn(this.regionList.stream().map(this::mapToDTO).collect(Collectors.toList()));
		
		assertThat(this.controller.readAll().getBody().isEmpty()).isFalse();
		
		verify(service, times(1)).readAll();
	}
	
	@Test
	public void testUpdate() {
		RegionDomain newRegion = new RegionDomain();
		newRegion.setName("Frelyord");
		newRegion.setDescription("Has Poros");
		RegionDTO updateRegion = new RegionDTO();
		updateRegion.setId(this.id);
		updateRegion.setName(newRegion.getName());
		updateRegion.setDescription(newRegion.getDescription());
		
		when(this.service.update(id, newRegion)).thenReturn(updateRegion);
		
		assertThat(new ResponseEntity<RegionDTO>(updateRegion, HttpStatus.ACCEPTED))
					.isEqualTo(this.controller.update(this.id, newRegion));
		
		verify(this.service, times(1)).update(this.id, newRegion);
	}
}
