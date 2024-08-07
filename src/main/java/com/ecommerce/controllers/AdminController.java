package com.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.model.Admin;
import com.ecommerce.repositories.AdminRepository;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	AdminRepository adminRepository;

	@PostMapping("/login")
	public ResponseEntity<String> LoginRequest(@RequestBody Admin admin) {

		System.out.println(" input details" + admin.getUsername());
		Admin adminDetails = adminRepository.findByUsername(admin.getUsername());
		System.out.println(adminDetails);
		if (adminDetails.getPassword().equals(admin.getPassword())) {
			return new ResponseEntity<>("logIn Successful", HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
	}
}
