/**
 * 
 */
package com.dhaval.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dhaval.dto.TopDriverDTO;
import com.dhaval.model.Person;
import com.dhaval.model.Ride;
import com.dhaval.service.PersonService;
import com.dhaval.service.RideService;

@RestController
public class RideController {

	@Autowired
	RideService rideService;
	
	@Autowired
	PersonService personService;

	@PostMapping(path = "/api/ride")
	public ResponseEntity<Ride> createNewRide(@RequestBody Ride ride) {
		return ResponseEntity.ok(rideService.save(ride));
	}

	@GetMapping(path = "/api/ride/{ride-id}")
	public ResponseEntity<Ride> getRideById(@PathVariable(name = "ride-id", required = true) Long rideId) {
		Ride ride = rideService.findById(rideId);
		if (ride != null)
			return ResponseEntity.ok(ride);
		return ResponseEntity.notFound().build();
	}

	/**
	 * This API returns the top 5 drivers with their email,name, total minutes,
	 * maximum ride duration in minutes. Only rides that starts and ends within the
	 * mentioned durations should be counted. Any rides where either start or
	 * endtime is outside the search, should not be considered.
	 * 
	 * DONT CHANGE METHOD SIGNATURE AND RETURN TYPES
	 * 
	 * @return
	 */
	@GetMapping(path = "/api/top-rides")
	public ResponseEntity<List<TopDriverDTO>> getTopDriver(@RequestParam(value = "max", defaultValue = "5") Long count,
			@RequestParam(value = "startTime", required = true) String startTm,
			@RequestParam(value = "endTime", required = true) String endTm) {
		
		List<TopDriverDTO> topDrivers = new ArrayList<TopDriverDTO>();
		
		String dateFormat = "yyyy-MM-dd'T'HH:mm:ss";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
		
		LocalDateTime startTime = LocalDateTime.parse(startTm, formatter);
		LocalDateTime endTime = LocalDateTime.parse(endTm, formatter);
		
		List<Ride> returnList = new ArrayList<>();
		List<Ride> internList = new ArrayList<>();
		int applicableRides = 0;
		List<Ride> getAllRide = rideService.getAll();
		for(Ride r : getAllRide) {
			LocalDateTime start = LocalDateTime.parse(r.getStartTime(), formatter);
			LocalDateTime end = LocalDateTime.parse(r.getEndTime(), formatter);
			
			if(start.compareTo(startTime) >=0 && endTime.compareTo(end) >=0) {
				internList.add(r);
				r.setDuration(ChronoUnit.SECONDS.between(start, end));
				applicableRides++;
			}
		}
		
		Collections.sort(internList);
		
		count = Math.min(applicableRides, count);
		
		for(int i=1; i<=count; i++) {
			
			returnList.add(internList.get(i-1));
			TopDriverDTO topDriver = new TopDriverDTO();
			Person person = personService.findById(internList.get(i-1).getDriver().getId());
			topDriver.setEmail(person.getEmail());
			topDriver.setName(person.getName());
			topDriver.setTotalRideDurationInSeconds(internList.get(i-1).getDuration());
			topDriver.setAverageDistance((double)internList.get(i-1).getDistance()/internList.get(i-1).getDuration());
			topDriver.setMaxRideDurationInSecods(internList.get(0).getDuration());
			
			topDrivers.add(topDriver);
		}
		
		return ResponseEntity.ok(topDrivers);

	}

}
