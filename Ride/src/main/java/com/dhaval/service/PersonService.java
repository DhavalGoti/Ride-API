package com.dhaval.service;

import java.util.List;
import com.dhaval.model.Person;

public interface PersonService {
  public List<Person> getAll();
  
  public Person save(Person p);
  
  public Person findById(Long personId);
  
}
