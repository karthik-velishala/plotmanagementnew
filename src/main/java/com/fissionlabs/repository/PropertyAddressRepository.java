package com.fissionlabs.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fissionlabs.model.PropertyAddress;

public interface PropertyAddressRepository extends
		MongoRepository<PropertyAddress, String> {

}
