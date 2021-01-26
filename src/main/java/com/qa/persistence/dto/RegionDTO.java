package com.qa.persistence.dto;

import java.util.List;

public class RegionDTO {
	private Long Id;
	private String name;
	private String description;
	private List<ChampionDTO> champList;
	
	public RegionDTO(String name, String description, List<ChampionDTO> champList) {
		super();
		this.name = name;
		this.description = description;
		this.champList = champList;
	}
	public RegionDTO() {
		super();
	}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<ChampionDTO> getChampList() {
		return champList;
	}
	public void setChampList(List<ChampionDTO> champList) {
		this.champList = champList;
	}
	
}
