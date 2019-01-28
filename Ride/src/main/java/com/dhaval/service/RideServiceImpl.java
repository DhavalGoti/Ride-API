package com.dhaval.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhaval.model.Ride;
import com.dhaval.repositories.RideRepository;

@Service
public class RideServiceImpl implements RideService {

	@Autowired
	RideRepository rideRepository;

	public Ride save(Ride ride) {
		return rideRepository.save(ride);
	}

	public Ride findById(Long rideId) {
		Optional<Ride> optionalRide = rideRepository.findById(rideId);
		if (optionalRide.isPresent()) {
			return optionalRide.get();
		} else
			return null;
	}

	
	public List<Ride> getAll() {
		List<Ride> rideList = new ArrayList<>();
		rideRepository.findAll().forEach(rideList::add);
		return rideList;

	}
}
