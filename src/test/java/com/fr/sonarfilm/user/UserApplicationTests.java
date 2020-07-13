package com.fr.sonarfilm.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fr.sonarfilm.user.dao.UserSonarRepository;
import com.fr.sonarfilm.user.models.UserSonar;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(Lifecycle.PER_CLASS)
class UserApplicationTests {
	
	private final static String URI = "/apiusers";
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext wac;

	@MockBean
	private UserSonarRepository userSonarRepo;
	
	@Autowired
	private TestRestTemplate template;

	
	@Autowired
	private ObjectMapper mapper;

	@BeforeAll
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).apply(springSecurity()).build();
	}
	
	 @Test
		public void shouldReturn401IfRegisterWithUserSonar() throws Exception { 
			
			 UserSonar newUser = new UserSonar("CorinneDu82", "coco");
			 String jsonRequest = mapper.writeValueAsString(newUser);
			 		 
		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/authenticate") 
				.content(jsonRequest)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError());
		
					
		}
	
	
	 @Test
	public void shouldReturn200IfRegisterWithUserSonar() throws Exception { 
		
		 UserSonar newUser = new UserSonar("Micheldu78", "michou");
		 String jsonRequest = mapper.writeValueAsString(newUser);
		 		 
	this.mockMvc
			.perform(MockMvcRequestBuilders.post("/register") 
			.content(jsonRequest)
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk()).andReturn();
				
	}

	 

}