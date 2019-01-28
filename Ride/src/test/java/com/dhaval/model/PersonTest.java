package com.dhaval.model;

import org.junit.Assert;
import org.junit.Test;

public class PersonTest {
	
	/**
	 * Email address test
	 */
	@Test
	public void testEmail() {
		Person myPerson = new Person();
		
		myPerson.setEmail("email");
		
		Assert.assertEquals("email", myPerson.getEmail());
		Assert.assertNotNull(myPerson.getEmail());
	}
	
	/**
	 * Name test
	 */
	@Test
	public void testName() {
		Person myPerson = new Person();
		
		myPerson.setName("name");
		
		Assert.assertEquals("name", myPerson.getName());
		Assert.assertNotNull(myPerson.getName());
	}
	
	/**
	 * ID test
	 */
	@Test
	public void testID() {
		Person myPerson = new Person();
		
		myPerson.setId(1l);
		
		Assert.assertNotNull(myPerson.getId());
	}
	
	/**
	 * RegistrationNumber test
	 */
	@Test
	public void testRegistrationNumber() {
		
		Person myPerson = new Person();
		
		myPerson.setRegistrationNumber("ABCD123");
		
		Assert.assertNotNull(myPerson.getRegistrationNumber());
		Assert.assertEquals("ABCD123", myPerson.getRegistrationNumber());
	}
	
	/**
	 * hashcode test
	 */
	@Test
	public void testHashCode() {
		
		Person myPerson = new Person();
		Person myPersonSecond = new Person();
		
		Assert.assertEquals(myPerson.hashCode(), myPersonSecond.hashCode());
		Assert.assertTrue(myPerson.equals(myPersonSecond));
		
		
		myPerson.setEmail("email1");
		myPerson.setName("name1");
		myPerson.setRegistrationNumber("NUMBER1");
		myPerson.setId(1l);
		
		myPersonSecond.setEmail("email2");
		myPersonSecond.setName("name2");
		myPersonSecond.setRegistrationNumber("NUMBER2");
		myPersonSecond.setId(2l);
		
		Assert.assertNotEquals(myPerson.hashCode(), myPersonSecond.hashCode());
		Assert.assertFalse(myPerson.equals(myPersonSecond));
	}


}
