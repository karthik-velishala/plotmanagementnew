package com.fissionlabs.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import com.fissionlabs.model.Owner;

public interface  OwnerRepository extends MongoRepository<Owner, String> {
	
	public List<Owner> findByEmail(String email);

}
