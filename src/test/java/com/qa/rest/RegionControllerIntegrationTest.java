package com.qa.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.HobbyWebApplication;
import com.qa.persistence.domain.ChampionDomain;
import com.qa.persistence.domain.RegionDomain;
import com.qa.persistence.dto.ChampionDTO;
import com.qa.persistence.dto.RegionDTO;

@SpringBootTest(classes = HobbyWebApplication.class)
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:schema-test.sql", "classpath:data-test.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class RegionControllerIntegrationTest {

	@Autowired
	private MockMvc mock;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private ObjectMapper jsonifier;
	
	private final Long TEST_ID = 1L;
	
	private RegionDTO mapToDTO(RegionDomain model) {
		return this.mapper.map(model, RegionDTO.class);
	}
	
	@Test
	public void testCreate() throws Exception {
		RegionDomain TEST_REGION = new RegionDomain();
		TEST_REGION.setName("Ionia");
		TEST_REGION.setDescription("Still Stands");
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.POST, "/region/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.jsonifier.writeValueAsString(TEST_REGION))
				.accept(MediaType.APPLICATION_JSON);
		TEST_REGION.setId(2L);
		
		
		// Assertion checks
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(mapToDTO(TEST_REGION)));
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isCreated();
	
		// Perform & Assert
		this.mock.perform(mockRequest)
		.andExpect(matchStatus)
		.andExpect(matchContent);
	}
	
	@Test
	public void testReadOneRegion() throws Exception {
		RegionDTO TEST_REGION = new RegionDTO();
		TEST_REGION.setId(TEST_ID);
		TEST_REGION.setName("Frelyord");
		TEST_REGION.setDescription("Is Cold");
		List<ChampionDTO> champList = new ArrayList<>();
		TEST_REGION.setChampList(champList);
		String content = this.mock.perform(request(HttpMethod.GET, ("/region/read/" + TEST_ID)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		
		assertEquals(this.jsonifier.writeValueAsString(TEST_REGION), content);
	}
	
	@Test
	public void testReadAll() throws Exception {
		List<RegionDTO> regionList = new ArrayList<>();
		RegionDTO TEST_REGION = new RegionDTO();
		TEST_REGION.setId(TEST_ID);
		TEST_REGION.setName("Frelyord");
		TEST_REGION.setDescription("Is Cold");
		List<ChampionDTO> champList = new ArrayList<>();
		regionList.add(TEST_REGION);
		TEST_REGION.setChampList(champList);
		
		String content = this.mock.perform(request(HttpMethod.GET, "/region/readAll").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertEquals(this.jsonifier.writeValueAsString(regionList), content);
	}
	@Test
	public void testUpdate() throws Exception {
		RegionDTO newRegion = new RegionDTO();
		newRegion.setName("Ionia");
		newRegion.setDescription("Still Stands");

		
		RegionDomain updateRegion = new RegionDomain();
		updateRegion.setId(TEST_ID);
		updateRegion.setName(newRegion.getName());
		updateRegion.setDescription(newRegion.getDescription());
		List<ChampionDomain> champList = new ArrayList<>();
		updateRegion.setChampList(champList);
		String result = this.mock
				.perform(request(HttpMethod.PUT, "/region/update/" + TEST_ID)
						.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
						.content(this.jsonifier.writeValueAsString(newRegion)))
				.andExpect(status().isAccepted()).andReturn().getResponse().getContentAsString();

		assertEquals(this.jsonifier.writeValueAsString(this.mapToDTO(updateRegion)), result);
		
	}
	@Test
	public void testDelete() throws Exception {
		// prepared REST request
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.DELETE, "/region/delete/" + TEST_ID);
		// Assertion checks
		//ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(" "));
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isNoContent();

		// Perform & Assert
		this.mock.perform(mockRequest)
		.andExpect(matchStatus);
	}
}
