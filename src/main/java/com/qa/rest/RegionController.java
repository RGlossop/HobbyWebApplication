package com.qa.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.persistence.domain.RegionDomain;
import com.qa.persistence.dto.RegionDTO;
import com.qa.service.RegionService;

@RestController
@CrossOrigin
@RequestMapping("/region")
public class RegionController {

	private RegionService service;

	@Autowired
	public RegionController(RegionService service) {
		super();
		this.service = service;
	}

	// GET, POST, PUT, DELETE
	// READ
	@GetMapping("/readAll")
	public ResponseEntity<List<RegionDTO>> readAll() {
		return ResponseEntity.ok(this.service.readAll());
	}

	// READ
	@GetMapping("/read/{id}")
	public ResponseEntity<RegionDTO> read(@PathVariable("id") Long id) {
		return ResponseEntity.ok(this.service.readOne(id));
	}

	// Create
	@PostMapping("/create")
	public ResponseEntity<RegionDTO> create(@RequestBody RegionDomain model) {
		return new ResponseEntity<RegionDTO>(this.service.create(model), HttpStatus.CREATED);
	}
	
	// Update
	@PutMapping("update/{id}")
	public ResponseEntity<RegionDTO> update(@PathVariable("id") Long id, @RequestBody RegionDomain model) {
		return new ResponseEntity<>(this.service.update(id, model), HttpStatus.ACCEPTED);
	}
	
	// Delete
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<RegionDTO> delete(@PathVariable("id") Long id) {
		return this.service.delete(id) ?
				new ResponseEntity<>(HttpStatus.NO_CONTENT) :
				new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
