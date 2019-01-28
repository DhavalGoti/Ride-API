package com.dhaval.controller;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.dhaval.model.Ride;
import com.dhaval.repositories.RideRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RideControllerTest {

	MockMvc mockMvc;

	@Mock
	private RideController rideController;

	@Autowired
	private TestRestTemplate template;

	@MockBean
	RideRepository rideRepository;

	@Before
	public void setup() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(rideController).build();
	}

	/**
	 * delete method test
	 * @throws Exception
	 */
	@Test
	public void testDeleteMethodTest_success() throws Exception {
		HttpEntity<Object> rideEntity = getHttpEntity("{\r\n" + 
				"\"startTime\": \"10:00:00T12:12:12\", \r\n" + 
				"\"startTime\": \"10:00:00T12:12:12\", \r\n" + 
				"\"endTime\": \"10:45:00T12:12:12\",\r\n" + 
				"\"distance\": \"50\",\r\n" + 
				"\"driver\":{\r\n" + 
				"    \"name\": \"driver 1\", \r\n" + 
				"	\"email\": \"driver@gmail.com\",\r\n" + 
				"	\"registrationNumber\": \"789\"\r\n" + 
				"  },\r\n" + 
				"\"rider\":{\r\n" + 
				"    \"name\": \"rider 1\", \r\n" + 
				"	\"email\": \"rider@gmail.com\",\r\n" + 
				"	\"registrationNumber\": \"1234AB\"\r\n" + 
				"  }\r\n" + 
				"}\r\n" + 
				"");
		ResponseEntity<Ride> response = template.postForEntity("/api/ride", rideEntity, Ride.class);
		// Delete this user
		rideRepository.deleteById(response.getBody().getId());
		Assert.assertEquals("driver 1", response.getBody().getDriver().getName());
		Assert.assertEquals(200, response.getStatusCode().value());
	}
	
	/**
	 * delete method test
	 * @throws Exception
	 */
	@Test
	public void testSavePerson_fail() throws Exception {
		HttpEntity<Object> rideEntity = getHttpEntity("{\r\n" + 
				"\"startTime\": \"10:00:00T12:12:12\", \r\n" + 
				"\"startTime\": \"10:00:00T12:12:12\", \r\n" + 
				"\"endTime\": \"10:45:00T12:12:12\",\r\n" + 
				"\"distance\": \"50\",\r\n" + 
				"\"driver\":{\r\n" + 
				"    \"name\": \"driver 1\", \r\n" + 
				"	\"email\": \"driver@gmail.com\",\r\n" + 
				"	\"registrationNumber\": \"789\"\r\n" + 
				"  },\r\n" + 
				"\"rider\":{\r\n" + 
				"    \"name\": \"rider 1\", \r\n" + 
				"	\"email\": \"rider@gmail.com\",\r\n" + 
				"	\"registrationNumber\": \"1234AB\"\r\n" + 
				"  }\r\n" + 
				"}\r\n" + 
				"");
		ResponseEntity<Ride> response = template.postForEntity("/api/ride", rideEntity, Ride.class);
		// Delete this user
		rideRepository.deleteById(response.getBody().getId());
		Assert.assertNotEquals("driver 2", response.getBody().getDriver().getName());
		Assert.assertEquals(200, response.getStatusCode().value());
	}
	
	/**
	 * findById method test
	 * @throws Exception
	 */
	@Test
	public void testGetRide_fail() throws Exception {
		Ride ride = rideRepository.findById(0L).orElse(null);
		Assert.assertNull(ride);
	}

	
	/**
	 *  findById method test
	 * @throws Exception
	 */
	@Test
	public void testGetRide_success() throws Exception {
		HttpEntity<Object> rideEntity = getHttpEntity("{\r\n" + 
				"\"startTime\": \"10:00:00T12:12:12\", \r\n" + 
				"\"startTime\": \"10:00:00T12:12:12\", \r\n" + 
				"\"endTime\": \"10:45:00T12:12:12\",\r\n" + 
				"\"distance\": \"50\",\r\n" + 
				"\"driver\":{\r\n" + 
				"    \"name\": \"driver 1\", \r\n" + 
				"	\"email\": \"driver@gmail.com\",\r\n" + 
				"	\"registrationNumber\": \"789\"\r\n" + 
				"  },\r\n" + 
				"\"rider\":{\r\n" + 
				"    \"name\": \"rider 1\", \r\n" + 
				"	\"email\": \"rider@gmail.com\",\r\n" + 
				"	\"registrationNumber\": \"1234AB\"\r\n" + 
				"  }\r\n" + 
				"}\r\n" + 
				"");
		ResponseEntity<Ride> response = template.postForEntity("/api/ride", rideEntity, Ride.class);
		Ride ride = rideRepository.findById(response.getBody().getId()).orElse(null);
		Assert.assertNotNull(ride);
	}
	
	/**
	 * @throws Exception
	 */
	@Test
	public void testGetPerson_fail() throws Exception {
		HttpEntity<Object> person = getHttpEntity("{\r\n" + 
				"\"startTime\": \"10:00:00T12:12:12\", \r\n" + 
				"\"endTime\": \"10:45:00T12:12:12\",\r\n" + 
				"\"distance\": \"50\",\r\n" + 
				"\"driver\":{\r\n" + 
				"    \"name\": \"driver 1\", \r\n" + 
				"	\"email\": \"driver@gmail.com\",\r\n" + 
				"	\"registrationNumber\": \"789\"\r\n" + 
				"  },\r\n" + 
				"\"rider\":{\r\n" + 
				"    \"name\": \"rider 1\", \r\n" + 
				"	\"email\": \"rider@gmail.com\",\r\n" + 
				"	\"registrationNumber\": \"1234AB\"\r\n" + 
				"  }\r\n" + 
				"}\r\n" + 
				"");
		
		ResponseEntity<Ride> response = template.postForEntity("/api/ride", person, Ride.class);
		ResponseEntity<Ride> responseForGet = template.getForEntity("/api/ride/"+response.getBody().getId(), Ride.class);
		
		Assert.assertNotNull(responseForGet.getBody());
	}
	
	@Test
	public void testGetPersonList_success() throws Exception {
		HttpEntity<Object> person = getHttpEntity("{\r\n" + 
				"\"startTime\": \"10:00:00T12:12:12\", \r\n" + 
				"\"endTime\": \"10:45:00T12:12:12\",\r\n" + 
				"\"distance\": \"50\",\r\n" + 
				"\"driver\":{\r\n" + 
				"    \"name\": \"driver 1\", \r\n" + 
				"	\"email\": \"driver@gmail.com\",\r\n" + 
				"	\"registrationNumber\": \"789\"\r\n" + 
				"  },\r\n" + 
				"\"rider\":{\r\n" + 
				"    \"name\": \"rider 1\", \r\n" + 
				"	\"email\": \"rider@gmail.com\",\r\n" + 
				"	\"registrationNumber\": \"1234AB\"\r\n" + 
				"  }\r\n" + 
				"}\r\n" + 
				"");
		ResponseEntity<Ride> response = template.postForEntity("/api/ride", person, Ride.class);
		
		ResponseEntity<Ride> responseForGet = template.postForEntity("/api/ride/"+response.getBody().getId(), person, Ride.class);
		Assert.assertNotNull(responseForGet);
	}
	
	private HttpEntity<Object> getHttpEntity(Object body) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new HttpEntity<Object>(body, headers);
	}

}
