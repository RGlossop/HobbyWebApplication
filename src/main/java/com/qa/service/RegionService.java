package com.qa.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.persistence.domain.RegionDomain;
import com.qa.persistence.dto.RegionDTO;
import com.qa.persistence.repos.RegionRepo;
import com.qa.utils.MyBeanUtils;

@Service
public class RegionService {
	private RegionRepo repo;
	private ModelMapper mapper;
	
	@Autowired
	public RegionService(RegionRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}
	
	private RegionDTO mapToDTO(RegionDomain model) {
		return this.mapper.map(model, RegionDTO.class);
	}
	
	// CRUD
	// Create
	public RegionDTO create(RegionDomain model) {
		return mapToDTO(this.repo.save(model));
	}
	// Read
	public RegionDTO readOne(Long id) {
		return this.mapToDTO(this.repo.findById(id).orElseThrow());
	}
	
	public List<RegionDTO> readAll() {
		List<RegionDomain> regionList = this.repo.findAll();
		List<RegionDTO> regionListDTO = regionList.stream().map(this::mapToDTO).collect(Collectors.toList());
	
		return regionListDTO;
	}
	// Update
	public RegionDTO update(long id, RegionDomain region) {
		RegionDomain updatedRegion = this.repo.findById(id).orElseThrow();
		MyBeanUtils.mergeNotNull(region, updatedRegion);
		
		return this.mapToDTO(this.repo.save(updatedRegion));
	}
	
	// Delete
	public boolean delete(Long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}
	
	
}
