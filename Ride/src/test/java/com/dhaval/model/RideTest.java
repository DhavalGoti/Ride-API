package com.dhaval.model;

import org.junit.Assert;
import org.junit.Test;

public class RideTest {

	@Test
	public void testHashCode() {
		
		Person myDriverFirst = new Person();
		Person myDriverSecond = new Person();
		
		Ride firstRide = new Ride();
		Ride secondRide = new Ride();
		
		Assert.assertEquals(firstRide.hashCode(), secondRide.hashCode());
		Assert.assertTrue(firstRide.equals(secondRide));
		
		myDriverFirst.setEmail("email1");
		myDriverFirst.setName("name1");
		myDriverFirst.setRegistrationNumber("NUMBER1");
		myDriverFirst.setId(1l);
		firstRide.setDriver(myDriverFirst);
		
		myDriverSecond.setEmail("email2");
		myDriverSecond.setName("name2");
		myDriverSecond.setRegistrationNumber("NUMBER2");
		myDriverSecond.setId(2l);
		firstRide.setDriver(myDriverSecond);
		
		Assert.assertNotEquals(firstRide.hashCode(), secondRide.hashCode());
		Assert.assertFalse(firstRide.equals(secondRide));
	}
}
