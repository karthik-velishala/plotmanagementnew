package com.fissionlabs.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Box;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.fissionlabs.model.PropertyLocation;

public interface PropertyLocationRepository extends MongoRepository<PropertyLocation,String> {
	
	 List<PropertyLocation> findByLocationWithin(Circle c);
	 
	   List<PropertyLocation> findByLocationWithin(Box b);
	   
	   List<PropertyLocation> findByLocationNear(Point point, Distance distance, Pageable pageable);
	   
	   List<PropertyLocation> findByLocationNear(Point point, Distance distance);
}
