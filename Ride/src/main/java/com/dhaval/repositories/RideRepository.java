package com.dhaval.repositories;

import com.dhaval.model.Ride;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource(exported = false)
public interface RideRepository extends CrudRepository<Ride, Long> {

}
