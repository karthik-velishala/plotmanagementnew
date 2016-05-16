package com.fissionlabs.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fissionlabs.model.Owner;
import com.fissionlabs.model.Property;

public interface PropertyRepository extends MongoRepository<Property, String> {
	public List<Property> findByCity(String city);

	public List<Property> findByOwner(Owner owner);
	
	public List<Property> findByStatus(String status);
}
