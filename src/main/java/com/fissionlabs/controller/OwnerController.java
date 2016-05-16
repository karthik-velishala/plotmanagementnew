package com.fissionlabs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fissionlabs.dto.OwnerDTO;
import com.fissionlabs.model.Owner;
import com.fissionlabs.repository.OwnerRepository;

@RestController
public class OwnerController {

	@Autowired
	OwnerRepository ownerRepository;

	/* getting list of all owners */
	@RequestMapping(value="/owner",method=RequestMethod.GET)
	@ResponseBody
	public Object getAllRecords(
			@RequestParam(value = "ownerId", required = false) String ownerId) {
		System.out.println(ownerId);
		System.out.println("entered");
		if (ownerId != null) {
			System.out.println("entered inside");
			Owner owner = ownerRepository.findOne(ownerId);
			System.out.println("owner is" + owner);
			return owner;
		} else {
			return ownerRepository.findAll();

		}
	}

	/*----creating owner----*/

	@RequestMapping(value = "/owner", method = RequestMethod.POST)
	@ResponseBody
	public String createOwner(@RequestBody OwnerDTO ownerDTO) {
		try {

			String owner_Id = null;

			Owner owner = new Owner();
			owner.setContactNo(ownerDTO.getContactNo());
			owner.setCountry(ownerDTO.getCountry());
			owner.setDob(ownerDTO.getDob());
			owner.setEmail(ownerDTO.getEmail());
			owner.setFirstName(ownerDTO.getFirstName());
			owner.setGender(ownerDTO.getGender());
			owner.setLastName(ownerDTO.getLastName());
			List<Owner> testowner = ownerRepository.findByEmail(ownerDTO
					.getEmail());
			if (testowner.size() != 0) {
				return "email Id already existing";
			} else {
				owner = ownerRepository.save(owner);
				owner_Id = owner.getId();
				return "owner successfully created with Id" + owner_Id;
			}
		} catch (Exception ex) {
			return ex.toString();
		}

	}

	/* deleting the owner */
	@RequestMapping(value = "/owner/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteOwner(@PathVariable String id) {
		try {
			Owner owner = ownerRepository.findOne(id);
			ownerRepository.delete(owner);
			return "owner successfully deleted having id" + id;
		} catch (Exception ex) {
			return "error deleting owner";
		}
	}

	/* updating the owner */
	@RequestMapping(value = "/owner/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public String updateOwner(@PathVariable String id,
			@RequestBody OwnerDTO ownerDTO) {
		try {

			Owner owner = ownerRepository.findOne(id);
			if (owner == null) {
				return "owner not present with entered id";
			}
			owner.setContactNo(ownerDTO.getContactNo());
			owner.setCountry(ownerDTO.getCountry());
			owner.setDob(ownerDTO.getDob());
			owner.setEmail(ownerDTO.getEmail());
			owner.setFirstName(ownerDTO.getFirstName());
			owner.setGender(ownerDTO.getGender());
			owner.setLastName(ownerDTO.getLastName());
			ownerRepository.save(owner);
		} catch (Exception ex) {
			return "Error updating the property: " + ex.toString();
		}
		return "Property succesfully updated!";
	}
}
