package com.qa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.HobbyWebApplication;

@SpringBootTest(classes = HobbyWebApplication.class)
public class RegionServiceUnitTest {

	@Autowired
	private RegionService service;
	
//	@MockBean
//	private RegionRepo repoMock;
//	
//	@MockBean
//	private ModelMapper mapper;
	
	
}
