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

import com.dhaval.model.Person;
import com.dhaval.repositories.PersonRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PersonControllerTest {

	MockMvc mockMvc;

	@Mock
	private PersonController personController;

	@Autowired
	private TestRestTemplate template;

	@Autowired
	PersonRepository personRepository;

	@Before
	public void setup() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
	}

	/**
	 * delete method positive test case
	 * @throws Exception
	 */
	@Test
	public void testSavePerson_success() throws Exception {
		HttpEntity<Object> person = getHttpEntity("{\"name\": \"test 1\", \"email\": \"test1@gmail.com\","
				+ " \"registrationNumber\": \"41DCT\"}");
		ResponseEntity<Person> response = template.postForEntity("/api/person", person, Person.class);
		personRepository.deleteById(response.getBody().getId());
		Assert.assertEquals("test 1", response.getBody().getName());
		Assert.assertEquals(200, response.getStatusCode().value());
	}
	
	/**
	 * delete method negative test case
	 * @throws Exception
	 */
	@Test
	public void testSavePerson_fail() throws Exception {
		HttpEntity<Object> person = getHttpEntity("{\"name\": \"test 2\", \"email\": \"test2@gmail.com\","
				+ " \"registrationNumber\": \"123WET\"}");
		ResponseEntity<Person> response = template.postForEntity("/api/person", person, Person.class);
		personRepository.deleteById(response.getBody().getId());
		Assert.assertNotEquals("Test", response.getBody().getName());
		Assert.assertEquals(200, response.getStatusCode().value());
	}
	
	/**
	 * get method positive test case
	 * @throws Exception
	 */
	@Test
	public void testGetPerson_success() throws Exception {
		HttpEntity<Object> personEntity = getHttpEntity("{\"name\": \"test 3\", \"email\": \"test2@gmail.com\","
				+ " \"registrationNumber\": \"123WET\"}");
		ResponseEntity<Person> response = template.postForEntity("/api/person", personEntity, Person.class);
		
		Person person = new Person();
		person.setName("test 4");
		person.setEmail("test4@gmail.com");
		person.setRegistrationNumber("123ABC");
		
		Person returnPerson = personRepository.save(person);
		
		Assert.assertEquals("test 4", returnPerson.getName());
		Assert.assertNotNull(returnPerson);
		Assert.assertEquals(200, response.getStatusCode().value());
	}

	
	/**
	 * get method negative test case
	 * @throws Exception
	 */
	@Test
	public void testGetPerson_fail() throws Exception {
		HttpEntity<Object> personEntity = getHttpEntity("{\"name\": \"test 3\", \"email\": \"test2@gmail.com\","
				+ " \"registrationNumber\": \"123WET\"}");
		ResponseEntity<Person> response = template.postForEntity("/api/person", personEntity, Person.class);
		
		Person person = new Person();
		person.setName("test 4");
		person.setEmail("test4@gmail.com");
		person.setRegistrationNumber("123ABC");
		
		Person returnPerson = personRepository.save(person);
		
		Assert.assertNotEquals("test 3", returnPerson.getName());
		Assert.assertNotNull(returnPerson);
		Assert.assertEquals(200, response.getStatusCode().value());
	}
	
	/**
	 * FindById method positive test case
	 * @throws Exception
	 */
	@Test
	public void testFindById_success() throws Exception {
		HttpEntity<Object> personEntity = getHttpEntity("{\"name\": \"test 4\", \"email\": \"test2@gmail.com\","
				+ " \"registrationNumber\": \"123WET\"}");
		ResponseEntity<Person> response = template.postForEntity("/api/person", personEntity, Person.class);
		
		Person person = new Person();
		person.setName("test 4");
		person.setEmail("test4@gmail.com");
		person.setRegistrationNumber("123ABC");
		
		Person returnPerson = personRepository.findById(response.getBody().getId()).orElse(null);
		
		Assert.assertEquals("test 4", returnPerson.getName());
		Assert.assertNotNull(returnPerson);
		Assert.assertEquals(200, response.getStatusCode().value());
	}
	
	/**
	 * FindById method negative test case
	 * @throws Exception
	 */
	@Test
	public void testFindByID_fail() throws Exception {
		HttpEntity<Object> personEntity = getHttpEntity("{\"name\": \"test 5\", \"email\": \"test2@gmail.com\","
				+ " \"registrationNumber\": \"123WET\"}");
		ResponseEntity<Person> response = template.postForEntity("/api/person", personEntity, Person.class);
		
		Person person = new Person();
		person.setName("test 4");
		person.setEmail("test4@gmail.com");
		person.setRegistrationNumber("123ABC");
		
		Person returnPerson = personRepository.findById(response.getBody().getId() + 1).orElse(null);
		
		Assert.assertNull(returnPerson);
		Assert.assertEquals(200, response.getStatusCode().value());
	}
	
	
	/**
	 * getList positive test case
	 * @throws Exception
	 */
	@Test
	public void testGetPersonList_success() throws Exception {
		HttpEntity<Object> person = getHttpEntity("{\"name\": \"test 4\", \"email\": \"test2@gmail.com\","
				+ " \"registrationNumber\": \"123WET\"}");
		template.postForEntity("/api/person", person, Person.class);
		
		ResponseEntity<List<Person>> responseForGet = template.exchange("/api/person", HttpMethod.GET, null, new ParameterizedTypeReference<List<Person>>() {});
		Assert.assertTrue(responseForGet != null);
		Assert.assertEquals(200, responseForGet.getStatusCode().value());
	}
	

	private HttpEntity<Object> getHttpEntity(Object body) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new HttpEntity<Object>(body, headers);
	}

}
