package com.dhaval.dto;

import org.junit.Assert;
import org.junit.Test;

public class TopDriverDTOTest {
	
	
	@Test
	public void testTopDriverDTO() {
		
		TopDriverDTO topDriverDTO = new TopDriverDTO("name","Email", 100l,50l,20.5);
		
		Assert.assertEquals("name", topDriverDTO.getName());
		Assert.assertEquals("Email", topDriverDTO.getEmail());
		Assert.assertNotNull(topDriverDTO.getTotalRideDurationInSeconds());
		Assert.assertNotNull(topDriverDTO.getMaxRideDurationInSecods());
		Assert.assertNotNull(topDriverDTO.getAverageDistance() == 20.5);
		
		TopDriverDTO topDriverDTOSecond = new TopDriverDTO("name","Email", null,null,null);
		Assert.assertNotEquals("name1", topDriverDTOSecond.getName());
		Assert.assertNotEquals("Email1", topDriverDTOSecond.getEmail());
		Assert.assertNull(topDriverDTOSecond.getTotalRideDurationInSeconds());
		Assert.assertNull(topDriverDTOSecond.getMaxRideDurationInSecods());
		Assert.assertNull(topDriverDTOSecond.getAverageDistance());
		
	}

}
