package com.qa.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.HobbyWebApplication;
import com.qa.persistence.domain.RegionDomain;
import com.qa.persistence.dto.RegionDTO;
import com.qa.persistence.repos.RegionRepo;

@SpringBootTest(classes = HobbyWebApplication.class)
public class RegionServiceUnitTest {

	@Autowired
	private RegionService service;
	
	@MockBean
	private RegionRepo repoMock;
	
	@MockBean
	private ModelMapper mapper;
	
	private List<RegionDomain> regionList;
	
	private RegionDomain testRegion;
	
	private RegionDomain testRegionWithID;
	
	private RegionDTO regionDTO;
	
	private final long ID = 1L;
	
	@BeforeEach
	public void init() {
		this.regionList = new ArrayList<>();
		this.regionList.add(testRegion);
		this.testRegion = new RegionDomain();
		this.testRegion.setName("Frelyord");
		this.testRegion.setDescription("Is Cold");
		this.testRegionWithID = new RegionDomain();
		this.testRegionWithID.setId(ID);
		this.testRegionWithID.setName(testRegion.getName());
		this.testRegionWithID.setDescription(testRegion.getDescription());
		this.regionDTO = new ModelMapper().map(testRegionWithID, RegionDTO.class);
	}
	
	@Test
	public void testCreateRegion() {
		RegionDomain newRegion = new RegionDomain();
		newRegion.setName("Frelyord");
		newRegion.setDescription("Is Cold");
		
		when(this.repoMock.save(testRegion)).thenReturn(testRegionWithID);
		when(this.mapper.map(testRegionWithID, RegionDTO.class)).thenReturn(regionDTO);
		
		assertThat(this.regionDTO).isEqualTo(this.service.create(newRegion));
	}
	
	@Test
	public void testDeleteRegion() {
		when(this.repoMock.existsById(ID)).thenReturn(false);
		
		assertThat(this.service.delete(ID)).isTrue();
		
		verify(this.repoMock, times(1)).deleteById(ID);
		verify(this.repoMock, times(1)).existsById(ID);
	}
	
	@Test
	public void testReadAll() {
		when(repoMock.findAll()).thenReturn(this.regionList);
		when(this.mapper.map(testRegionWithID, RegionDTO.class)).thenReturn(regionDTO);
		
		assertThat(this.service.readAll().isEmpty()).isFalse();
		
		verify(repoMock, times(1)).findAll();
	}
	
	@Test
	public void testReadOne() {
		when(this.repoMock.findById(this.ID)).thenReturn(Optional.of(testRegionWithID));
		when(this.mapper.map(testRegionWithID, RegionDTO.class)).thenReturn(regionDTO);
		
		assertThat(this.regionDTO).isEqualTo(this.service.readOne(this.ID));
		
		verify(this.repoMock, times(1)).findById(this.ID);
	}
	
	@Test
	public void testUpdate() {
		final long ID = 1L;
		RegionDomain newRegion = new RegionDomain();
		newRegion.setName("Ionia");
		newRegion.setDescription("Still Stands");
		RegionDomain region = new RegionDomain();
		region.setId(ID);
		region.setName("Frelyord");
		region.setDescription("Is Cold");
		RegionDomain updatedRegion = new RegionDomain();
		updatedRegion.setId(ID);
		updatedRegion.setName(newRegion.getName());
		updatedRegion.setDescription(newRegion.getDescription());
		RegionDTO updatedDTO = new RegionDTO(ID, updatedRegion.getName(), updatedRegion.getDescription(), null);
	
	
		when(this.repoMock.findById(this.ID)).thenReturn(Optional.of(region));
		
		when(this.repoMock.save(updatedRegion)).thenReturn(updatedRegion);
		when(this.mapper.map(updatedRegion, RegionDTO.class)).thenReturn(updatedDTO);
		
		assertThat(updatedDTO).isEqualTo(this.service.update(ID, newRegion));
	}
}
