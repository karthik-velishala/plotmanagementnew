package com.fissionlabs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fissionlabs.dto.PropertyDTO;
import com.fissionlabs.model.Owner;
import com.fissionlabs.model.Property;
import com.fissionlabs.repository.OwnerRepository;
import com.fissionlabs.repository.PropertyRepository;

@Controller
public class PropertyController {
	@Autowired
	PropertyRepository propertyRepository;
	@Autowired
	OwnerRepository ownerRepository;

	/* getting list of all properties */

	@RequestMapping("/property/get")
	@ResponseBody
	public Object getAllRecords(
			@RequestParam(value = "id", required = false) String id) {
		if (id != null) {
			Property property = propertyRepository.findOne(id);
			return property;
		}
		return propertyRepository.findAll();

	}

	/* creating new property */

	@RequestMapping(value = "/property/create", method = RequestMethod.POST)
	@ResponseBody
	public String create(@RequestBody PropertyDTO createProperty) {
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

			Owner owner = createProperty.getOwner();
			String email = owner.getEmail();
			Owner testowner = ownerRepository.findOne(owner.getOwner_Id());
			List<Owner> testownerByemail = ownerRepository.findByEmail(email);

			if ((testowner == null) && ((testownerByemail).size() == 0)) {

				ownerRepository.save(owner);
			} else
				return "old owner or email already existed";

			property.setOwner(createProperty.getOwner());
			property = propertyRepository.save(property);
			propertyId = property.getPropertyId();
			System.out.println("entered");

		} catch (Exception ex) {
			return "Error creating the property: " + ex.toString();
		}
		return "Property succesfully created with id = " + propertyId;
	}

	/* updating a property */

	@RequestMapping(value = "/property/update/{propertyId}", method = RequestMethod.PUT)
	@ResponseBody
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
			Owner testIfExists = ownerRepository.findOne(owner.getOwner_Id());
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
	
	@RequestMapping(value = "/property/delete/{propertyId}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable String propertyId) {
		try {
			Property property = new Property(propertyId);
			propertyRepository.delete(property);
		} catch (Exception ex) {
			return "Error deleting the property:" + ex.toString();
		}
		return "Property succesfully deleted!";
	}

	@RequestMapping(value = "/property/get-by-city", method = RequestMethod.GET)
	@ResponseBody
	public List<Property> getByCity(@RequestParam(value = "city") String city) {

		List<Property> property = propertyRepository.findByCity(city);
		return property;

	}

	/* getting list of all owners */
	
	@RequestMapping(value = "/owners", method = RequestMethod.GET)
	@ResponseBody
	public List<Owner> getAllOwners() {
		List<Owner> owners = ownerRepository.findAll();
		return owners;
	}

}
