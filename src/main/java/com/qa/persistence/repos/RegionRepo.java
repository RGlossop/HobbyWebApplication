package com.qa.persistence.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.persistence.domain.RegionDomain;

@Repository
public interface RegionRepo extends JpaRepository<RegionDomain, Long> {

}
