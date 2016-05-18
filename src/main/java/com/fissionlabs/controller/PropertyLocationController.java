package com.fissionlabs.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.GeospatialIndex;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fissionlabs.dto.PropertyLocationDTO;
import com.fissionlabs.model.GenericResponse;
import com.fissionlabs.model.PropertyLocation;
import com.fissionlabs.repository.PropertyLocationRepository;

@RestController
public class PropertyLocationController {

	@Autowired
	PropertyLocationRepository propertyLocationRepository;
	@Autowired
	MongoTemplate template;

	/* creating property with location details(latitude and longitude) */
	@RequestMapping(value = "/property_location", method = RequestMethod.POST)
	public GenericResponse createPropertyByLocation(
			@RequestBody PropertyLocationDTO property) {
		GenericResponse res = new GenericResponse();

		try {
			PropertyLocation propertyLocation = new PropertyLocation();
			String id = null;
			propertyLocation.setLocation(property.getLocation());

			propertyLocation.setSource(property.getSource());
			propertyLocation.setTags(property.getTags());
			Date date = new Date();
			propertyLocation.setTimestamp(new Timestamp(date.getTime()));

			PropertyLocation savePropertyLocation = propertyLocationRepository
					.save(propertyLocation);
			id = savePropertyLocation.getId();
			res.setSuccess(true);
			res.setData(savePropertyLocation);
			res.setMessage("property Successfully created with id " + id);
			return res;
		} catch (Exception ex) {
			res.setSuccess(false);
			res.setMessage("Exception is" + ex.toString());
			res.setData(null);
			return res;
		}

	}

	/* getting list of all properties */
	@RequestMapping(value = "/property_location", method = RequestMethod.GET)
	public Object getAllProperties(
			@RequestParam(value = "id", required = false) String id) {
		try {
			if (id != null) {
				PropertyLocation property = propertyLocationRepository
						.findOne(id);
				return property;
			}
			return propertyLocationRepository.findAll();
		} catch (Exception ex) {
			return ex.toString();
		}

	}

	/* getting list of all properties near by a particular location */
	@RequestMapping(value = "/nearbyproperties", method = RequestMethod.GET)
	public List<PropertyLocation> getNearByproperties(
			@RequestParam("location") String location,
			@RequestParam(value = "proximity", required = false) Double proximity,
			@RequestParam("limit") Integer limit) {
		template.indexOps(PropertyLocation.class).ensureIndex(
				new GeospatialIndex("location"));
		String[] pos = location.split(",");
		Point point = new Point(Double.parseDouble(pos[0]),
				Double.parseDouble(pos[1]));

		List<PropertyLocation> locations = propertyLocationRepository
				.findByLocationNear(point, new Distance(proximity,
						Metrics.KILOMETERS), new PageRequest(0, limit));
System.out.println(locations.size());
		return locations;

	}
}
