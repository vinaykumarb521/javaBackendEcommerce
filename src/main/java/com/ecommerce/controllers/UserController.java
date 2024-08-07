package com.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.model.User;
import com.ecommerce.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user) {

		if (userService.findByUsername(user.getUsername()) != null) {
			return new ResponseEntity<>("User already exist", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(userService.registerUser(user), HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody User user) {

		User userLoginDetails = userService.findByUsername(user.getUsername());
		if (userLoginDetails == null) {
			return new ResponseEntity<>("User not exist", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(userLoginDetails, HttpStatus.OK);
	}

}
