package com.dhaval.repositories;

import java.util.Optional;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.dhaval.model.Person;


@RestResource(exported=false)
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {
  Optional<Person> findById(Long id);
}

