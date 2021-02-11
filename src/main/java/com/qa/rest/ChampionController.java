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

import com.qa.persistence.domain.ChampionDomain;
import com.qa.persistence.dto.ChampionDTO;
import com.qa.service.ChampionService;

@RestController
@CrossOrigin
@RequestMapping("/champion")
public class ChampionController {

	private ChampionService service;
	
	@Autowired
	public ChampionController(ChampionService service) {
		super();
		this.service = service;
	}
	
	// GET, POST, PUT, DELETE
	
	// READ
	@GetMapping("/readAll")
	public ResponseEntity<List<ChampionDTO>> readAll() {
		return ResponseEntity.ok(this.service.readAll());
	}
	
	// READ
	@GetMapping("/read/{id}")
	public ResponseEntity<ChampionDTO> readChamp(@PathVariable("id") Long id) {
		return ResponseEntity.ok(this.service.readOne(id));
	}
	
	// CREATE
	@PostMapping("/create")
	public ResponseEntity<ChampionDTO> create(@RequestBody ChampionDomain champ) {
		return new ResponseEntity<ChampionDTO>(this.service.create(champ), HttpStatus.CREATED);
	}
	
	// UPDATE
	@PutMapping("update/{id}")
	public ResponseEntity<ChampionDTO> update(@PathVariable("id") Long id, @RequestBody ChampionDomain champ) {
		return new ResponseEntity<>(this.service.update(id, champ), HttpStatus.ACCEPTED);
	}
	
	// DELETE
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ChampionDTO> deleteChamp(@PathVariable("id") Long id) {
		return this.service.delete(id) ?
				new ResponseEntity<>(HttpStatus.NO_CONTENT) :
				new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
