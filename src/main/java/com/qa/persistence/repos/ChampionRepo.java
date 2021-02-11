package com.qa.persistence.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.persistence.domain.ChampionDomain;

@Repository
public interface ChampionRepo extends JpaRepository<ChampionDomain, Long>{
	// CRUD
	
	// create
	// reads
	// updates
	// deletes
	
	// custom SQL queries
}
