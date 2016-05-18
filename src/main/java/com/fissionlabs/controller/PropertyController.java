package com.fissionlabs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fissionlabs.dto.PropertyDTO;
import com.fissionlabs.model.GenericResponse;
import com.fissionlabs.model.Owner;
import com.fissionlabs.model.Property;
import com.fissionlabs.repository.OwnerRepository;
import com.fissionlabs.repository.PropertyRepository;

@RestController
public class PropertyController {
	@Autowired
	PropertyRepository propertyRepository;
	@Autowired
	OwnerRepository ownerRepository;

	/* getting list of all properties */

	@RequestMapping(value = "/property", method = RequestMethod.GET)
	public Object getAllRecords(
			@RequestParam(value = "id", required = false) String id) {
		if (id != null) {
			System.out.println(id);
			Property property = propertyRepository.findOne(id);
			if(property!=null)
			return property;
			else
				return "property not found";
		}
		return propertyRepository.findAll();

	}

	/* creating new property */

	@RequestMapping(value = "/property", method = RequestMethod.POST)
	public GenericResponse create(@RequestBody PropertyDTO createProperty) {
		GenericResponse res = new GenericResponse();
		@SuppressWarnings("unused")
		String propertyId = null;
		try {
			Property property = new Property();
			property.setArea(createProperty.getArea());
			property.setCity(createProperty.getCity());
			property.setNoOfRooms(createProperty.getNoOfRooms());
			property.setPrice(createProperty.getPrice());
			property.setPropertyName(createProperty.getPropertyName());
			property.setPropertyType(createProperty.getPropertyType());
			property.setHno(createProperty.getHno());
			property.setLandmark(createProperty.getLandmark());
			property.setRoadno(createProperty.getRoadno());
			property.setStatus(createProperty.getStatus());

			Owner owner = createProperty.getOwner();
			String email = owner.getEmail();
			Owner testowner = ownerRepository.findOne(owner.getId());
			List<Owner> testownerByemail = ownerRepository.findByEmail(email);

			if ((testowner == null) && ((testownerByemail).size() == 0)) {

				owner = ownerRepository.save(owner);
			}
			property.setOwner(createProperty.getOwner());
			property = propertyRepository.save(property);
			propertyId = property.getPropertyId();
			System.out.println("entered");
			res.setSuccess(true);
			res.setMessage("property successfully created");
			res.setData(property);

		} catch (Exception ex) {
			// return "Error creating the property: " + ex.toString();
			res.setMessage(ex.toString());
			res.setSuccess(false);
			res.setData(null);
			return res;
		}
		// return "Property succesfully created with id = " + propertyId;
		return res;
	}

	/* updating a property */

	@RequestMapping(value = "/property/{propertyId}", method = RequestMethod.PUT)
	public String updateProperty(@RequestBody PropertyDTO updateProperty,
			@PathVariable String propertyId) {
		try {

			Property property = propertyRepository.findOne(propertyId);

			if (property == null) {
				return "Property doesnot exists";
			}
			System.out.println("emtered");

			property.setArea(updateProperty.getArea());
			property.setCity(updateProperty.getCity());
			property.setNoOfRooms(updateProperty.getNoOfRooms());
			property.setPrice(updateProperty.getPrice());
			property.setPropertyName(updateProperty.getPropertyName());
			property.setPropertyType(updateProperty.getPropertyType());
			Owner owner = updateProperty.getOwner();
			// System.out.println(owner.getOwner_Id());
			Owner testIfExists = ownerRepository.findOne(owner.getId());
			System.out.println(testIfExists);
			if (testIfExists == null) {
				// System.out.println("owner not present");
				ownerRepository.save(owner);
			}

			property.setOwner(owner);
			propertyRepository.save(property);
		} catch (Exception ex) {
			return "Error updating the property: " + ex.toString();
		}
		return "Property succesfully updated!";
	}

	/* deleting a property */

	@RequestMapping(value = "/property/{propertyId}", method = RequestMethod.DELETE)
	public String delete(@PathVariable String propertyId) {
		try {
			Property property = new Property(propertyId);
			propertyRepository.delete(property);
		} catch (Exception ex) {
			return "Error deleting the property:" + ex.toString();
		}
		return "Property succesfully deleted!";
	}

	/* getting property by city */
	@RequestMapping(value = "/property/get-by-city", method = RequestMethod.GET)
	public List<Property> getByCity(@RequestParam(value = "city") String city) {

		List<Property> property = propertyRepository.findByCity(city);
		return property;

	}

	/* getting owner details of property */
	@RequestMapping(value = "/property/owner/{propertyId}", method = RequestMethod.GET)
	public Object getOwnerDetails(@PathVariable String propertyId) {
		Property property = propertyRepository.findOne(propertyId);
		if (property == null) {
			return "property with entered Id doesnot exists";
		}
		Owner owner = property.getOwner();
		return owner;
	}

	/* getting all properties by status */
	@RequestMapping(value = "/property/{status}", method = RequestMethod.GET)
	public List<Property> getAvailableProperties(@PathVariable String status) {
		List<Property> properties = propertyRepository.findByStatus(status);
		return properties;
	}

}
