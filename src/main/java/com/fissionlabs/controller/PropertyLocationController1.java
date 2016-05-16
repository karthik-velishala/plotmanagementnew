package com.fissionlabs.controller;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.GeospatialIndex;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fissionlabs.dto.PropertyLocationDTO;
import com.fissionlabs.model.GenericResponse;
import com.fissionlabs.model.PropertyLocation;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
public class PropertyLocationController1 {

	@Autowired
	MongoTemplate template;

	/* creating property with location details(latitude and longitude) */
	@RequestMapping(value = "/property", method = RequestMethod.POST)
	public GenericResponse createProperty(
			@RequestBody PropertyLocationDTO property) {
		GenericResponse res = new GenericResponse();
		RestTemplate rt = new RestTemplate();
		try {
			PropertyLocation propertyLocation = new PropertyLocation();

			propertyLocation.setLocation(property.getLocation());

			propertyLocation.setSource(property.getSource());
			propertyLocation.setTags(property.getTags());
			Date date = new Date();
			propertyLocation.setTimestamp(new Timestamp(date.getTime()));
			propertyLocation.setAccuracy(property.getAccuracy());
			propertyLocation.setMeta(property.getMeta());
			final String uri = "http://localhost:8080/actors";
			ResponseEntity<GenericResponse> response = rt.postForEntity(uri,
					propertyLocation, GenericResponse.class);
			/* getting id of stored record */
			PropertyLocation propertyLocation1 = (PropertyLocation) response
					.getBody().getData();
			String id = propertyLocation1.getId();
			System.out.println(id);
			return response.getBody();

		} catch (Exception ex) {
			res.setSuccess(false);
			res.setMessage("Exception is" + ex.toString());
			res.setData(null);
			return res;
		}
	}

	/* getting list of all properties near by a particular location */
	@RequestMapping(value = "/nearby", method = RequestMethod.GET)
	public String getNearByproperties(
			@RequestParam("location") String location,
			@RequestParam(value = "proximity", required = false) Double proximity,
			@RequestParam("limit") Integer limit) {
		template.indexOps(PropertyLocation.class).ensureIndex(
				new GeospatialIndex("location"));
		String[] pos = location.split(",");
		@SuppressWarnings("unused")
		Point point = new Point(Double.parseDouble(pos[0]),
				Double.parseDouble(pos[1]));
		String uri = "http://localhost:8080/actors?proximity=" + proximity
				+ "&limit=" + limit + "&location=" + location;
		RestTemplate rt = new RestTemplate();
		String result = rt.getForObject(uri, String.class);
		Gson gson = new GsonBuilder().serializeNulls().create();
		PropertyLocation properties = gson.fromJson(result,
				PropertyLocation.class);
		return gson.toJson(properties);

	}
}
