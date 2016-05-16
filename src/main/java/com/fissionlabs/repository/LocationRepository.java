/*package com.fissionlabs.repository;

import java.util.List;

import org.springframework.data.geo.Box;
import org.springframework.data.geo.Circle;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.fissionlabs.model.LocationNew;

public interface LocationRepository extends MongoRepository<LocationNew,String>{
	 List<LocationNew> findByPositionWithin(Circle c);
	 
	   List<LocationNew> findByPositionWithin(Box b);

}
*/