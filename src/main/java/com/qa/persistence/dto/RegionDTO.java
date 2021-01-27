package com.qa.persistence.dto;

import java.util.List;


public class RegionDTO {
	private Long id;
	private String name;
	private String description;
	private List<ChampionDTO> champList;
	
	public RegionDTO() {
		super();
	}
	public RegionDTO(Long id, String name, String description, List<ChampionDTO> champList) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.champList = champList;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	@Override
	public String toString() {
		return "RegionDTO [id=" + id + ", name=" + name + ", description=" + description + ", champList=" + champList
				+ "]";
	}
}
