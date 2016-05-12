/*package com.fissionlabs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fissionlabs.dto.User1DTO;
import com.fissionlabs.model.User1;
import com.fissionlabs.repository.UserRepository;

@RestController
public class UserController {

	@Autowired
	UserRepository userRepository;

	@RequestMapping(value = "/user/create", method = RequestMethod.POST)
	@ResponseBody
	public String createUser(@RequestBody User1DTO userDTO) {

		try {
			User user = new User1();
			user.setName(userDTO.getName());
			user.setEmail(userDTO.getEmail());
			userRepository.save(user);
			return "user successfully created";
		} catch (Exception ex) {
			return ex.toString();
		}

	}
}
*/