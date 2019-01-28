/**
 * 
 */
package com.dhaval.service;

import java.util.List;
import com.dhaval.model.Ride;


public interface RideService {
  
  public Ride save(Ride ride);
  
  public Ride findById(Long rideId);
  
  public List<Ride> getAll();

}
