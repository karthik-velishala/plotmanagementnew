package com.fissionlabs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fissionlabs.repository.OwnerRepository;

@Controller
public class OwnerController {
	
	@Autowired
	OwnerRepository ownerRepository;
	@RequestMapping(value="/owner/get")

}
