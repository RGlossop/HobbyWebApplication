package com.qa.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.HobbyWebApplication;
import com.qa.persistence.domain.ChampionDomain;
import com.qa.persistence.domain.RegionDomain;
import com.qa.persistence.dto.ChampionDTO;

@SpringBootTest(classes = HobbyWebApplication.class)
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:schema-test.sql", "classpath:data-test.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles(profiles = "test")
public class ChampionControllerIntegrationTest {
	@Autowired
	private MockMvc mock;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private ObjectMapper jsonifier;
	
	private ChampionDTO mapToDTO(ChampionDomain model) {
		return this.mapper.map(model, ChampionDTO.class);
	}
	// =====================================
	// TESTS
	// =====================================
	private final Long TEST_ID = 1L;
	// CREATE
	@Test
	public void testCreateChamp() throws Exception {
		ChampionDomain TEST_CHAMP = new ChampionDomain();
		TEST_CHAMP.setName("Bard");
		TEST_CHAMP.setRole("Catcher");
		// prepared REST request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.POST, "/champion/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.jsonifier.writeValueAsString(TEST_CHAMP))
				.accept(MediaType.APPLICATION_JSON);
		TEST_CHAMP.setId(2L);
		
		// Assertion checks
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(mapToDTO(TEST_CHAMP)));
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isCreated();
	
		// Perform & Assert
		this.mock.perform(mockRequest)
		.andExpect(matchStatus)
		.andExpect(matchContent);
	}
	// READ
	@Test
	public void testReadOneChamp() throws Exception {
		ChampionDTO TEST_CHAMP = new ChampionDTO();
		TEST_CHAMP.setId(TEST_ID);
		TEST_CHAMP.setName("Braum");
		TEST_CHAMP.setRole("Support");
		// prepared REST request

		String content = this.mock.perform(request(HttpMethod.GET, ("/champion/read/" + TEST_ID)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		assertEquals(this.jsonifier.writeValueAsString(TEST_CHAMP), content);
	}
	// READ
	@Test
	public void testReadAllChamps() throws Exception {
		List<ChampionDTO> champList = new ArrayList<>();
		ChampionDomain TEST_CHAMP = new ChampionDomain();
		TEST_CHAMP.setId(TEST_ID);
		TEST_CHAMP.setName("Braum");
		TEST_CHAMP.setRole("Support");
		champList.add(this.mapToDTO(TEST_CHAMP));

		String content = this.mock.perform(request(HttpMethod.GET, "/champion/readAll").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertEquals(this.jsonifier.writeValueAsString(champList), content);
	}
	// UPDATE
	@Test
	public void testUpdateChamp() throws Exception {
		ChampionDTO newChamp = new ChampionDTO();
		newChamp.setName("Braum");
		newChamp.setRole("Support");
		ChampionDomain updateChamp = new ChampionDomain();
		updateChamp.setId(TEST_ID);
		updateChamp.setName(newChamp.getName());
		updateChamp.setRole(newChamp.getRole());
		
		String result = this.mock
				.perform(request(HttpMethod.PUT, "/champion/update/" + TEST_ID)
						.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
						.content(this.jsonifier.writeValueAsString(newChamp)))
				.andExpect(status().isAccepted()).andReturn().getResponse().getContentAsString();

		assertEquals(this.jsonifier.writeValueAsString(this.mapToDTO(updateChamp)), result);
	}
	// DELETE
	@Test
	public void testDeleteChamp() throws Exception {
		// prepared REST request
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.DELETE, "/champion/delete/" + TEST_ID);
		// Assertion checks
		//ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(" "));
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isNoContent();

		// Perform & Assert
		this.mock.perform(mockRequest)
		.andExpect(matchStatus);
	}
}
