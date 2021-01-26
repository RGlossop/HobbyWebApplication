package com.qa.persistence.domain;

import javax.persistence.Entity;
import java.util.List;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class RegionDomain {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@NotNull
	private String name;

	@NotNull
	private String description;

	@OneToMany(mappedBy = "region", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<ChampionDomain> champList;

	public RegionDomain(String name, String description, List<ChampionDomain> champList) {
		super();
		this.name = name;
		this.description = description;
		this.champList = champList;
	}

	public RegionDomain() {
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

	public List<ChampionDomain> getChampList() {
		return champList;
	}

	public void setChampList(List<ChampionDomain> champList) {
		this.champList = champList;
	}

	@Override
	public String toString() {
		return "RegionDomain [Id=" + Id + ", name=" + name + ", description=" + description + ", champList=" + champList
				+ "]";
	}

}
