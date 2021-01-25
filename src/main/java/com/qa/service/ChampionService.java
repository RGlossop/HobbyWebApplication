package com.qa.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.persistence.domain.ChampionDomain;
import com.qa.persistence.dto.ChampionDTO;
import com.qa.persistence.repos.ChampionRepo;
import com.qa.utils.MyBeanUtils;

@Service
public class ChampionService {

	private ChampionRepo repo;
	private ModelMapper mapper;
	
	@Autowired
	public ChampionService(ChampionRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}
	
	private ChampionDTO mapToDTO(ChampionDomain model) {
		return this.mapper.map(model, ChampionDTO.class);
	}
	// CRUD
	// Create
	public ChampionDTO create(ChampionDomain model) {
		return mapToDTO(this.repo.save(model));
	}
	// Read
	public ChampionDTO readOne(Long id) {
		return this.mapToDTO(this.repo.findById(id).orElseThrow());
	}
	
	public List<ChampionDTO> readAll() {
		List<ChampionDomain> champList = this.repo.findAll();
		List<ChampionDTO> champListDTO = champList.stream().map(this::mapToDTO).collect(Collectors.toList());
		return champListDTO;
	}
	// Update
	public ChampionDTO update(long id, ChampionDomain champ) {
		ChampionDomain updatedChamp = this.repo.findById(id).orElseThrow();
		MyBeanUtils.mergeNotNull(champ, updatedChamp);
		return this.mapToDTO(this.repo.save(updatedChamp));
	}
	// Delete
	public boolean delete(Long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}
}
