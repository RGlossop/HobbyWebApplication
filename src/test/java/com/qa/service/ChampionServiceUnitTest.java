package com.qa.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.HobbyWebApplication;
import com.qa.persistence.domain.ChampionDomain;
import com.qa.persistence.dto.ChampionDTO;
import com.qa.persistence.repos.ChampionRepo;

@SpringBootTest(classes = HobbyWebApplication.class)
public class ChampionServiceUnitTest {

	@Autowired
	private ChampionService service;
	
	@MockBean
	private ChampionRepo repoMock;
	
	@MockBean
	private ModelMapper mapper;
	
	private List<ChampionDomain> champList;
	
	private ChampionDomain testChamp;
	
	private ChampionDomain testChampWithID;
	
	private ChampionDTO champDTO;
	
	private final long ID = 1L;
	@Test
	@Disabled
	private void testMapToDTO() {

	}
	@BeforeEach
	void init() {
		this.champList = new ArrayList<>();
		this.champList.add(testChamp);
		this.testChamp = new ChampionDomain();
		testChamp.setName("Braum");
		testChamp.setRole("Support");
		this.testChampWithID = new ChampionDomain();
		this.testChampWithID.setId(ID);
		this.testChampWithID.setName(testChamp.getName());
		this.testChampWithID.setRole(testChamp.getRole());
		this.champDTO = new ModelMapper().map(testChampWithID, ChampionDTO.class);
	}
	
	@Test
	public void testCreateChamp() {
		ChampionDomain newChamp = new ChampionDomain();
		newChamp.setName("Braum");
		newChamp.setRole("Support");
		
		when(this.repoMock.save(testChamp)).thenReturn(testChampWithID);
		when(this.mapper.map(testChampWithID, ChampionDTO.class)).thenReturn(champDTO);
		
		assertThat(this.champDTO).isEqualTo(this.service.create(newChamp));
		
		verify(this.repoMock, times(1)).save(this.testChamp);
	}
	
	@Test
	void testDeleteChamp() {
		when(this.repoMock.existsById(ID)).thenReturn(false);
		
		assertThat(this.service.delete(ID)).isTrue();
		
		verify(this.repoMock, times(1)).deleteById(ID);
		verify(this.repoMock, times(1)).existsById(ID);
	}
	
	@Test
	public void testReadAll() {
		when(repoMock.findAll()).thenReturn(this.champList);
		when(this.mapper.map(testChampWithID, ChampionDTO.class)).thenReturn(champDTO);
		
		assertThat(this.service.readAll().isEmpty()).isFalse();
		
		verify(repoMock, times(1)).findAll();
	}
	
	@Test
	public void testReadOne() {
		when(this.repoMock.findById(this.ID)).thenReturn(Optional.of(testChampWithID));
		when(this.mapper.map(testChampWithID, ChampionDTO.class)).thenReturn(champDTO);
		
		assertThat(this.champDTO).isEqualTo(this.service.readOne(this.ID));
		
		verify(this.repoMock, times(1)).findById(this.ID);
	}
	@Test
	public void testUpdate() {
		final long ID = 1L;
		ChampionDomain newChamp = new ChampionDomain();
		newChamp.setName("Yuumi");
		newChamp.setRole("Support");
		ChampionDomain champion = new ChampionDomain();
		champion.setId(ID);
		champion.setName("Braum");
		champion.setRole("Tank");
		ChampionDomain updatedChamp = new ChampionDomain();
		updatedChamp.setId(ID);
		updatedChamp.setName(newChamp.getName());
		updatedChamp.setRole(newChamp.getRole());
		ChampionDTO updatedDTO = new ChampionDTO(ID, updatedChamp.getName(), updatedChamp.getRole());
	
		when(this.repoMock.findById(this.ID)).thenReturn(Optional.of(champion));
		
		when(this.repoMock.save(updatedChamp)).thenReturn(updatedChamp);
		when(this.mapper.map(updatedChamp, ChampionDTO.class)).thenReturn(updatedDTO);
		
		assertThat(updatedDTO).isEqualTo(this.service.update(this.ID, newChamp));
		
		verify(this.repoMock, times(1)).findById(1L);
		verify(this.repoMock, times(1)).save(updatedChamp);
	}
}
