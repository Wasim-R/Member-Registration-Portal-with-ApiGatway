package com.cognizant.user.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.user.entity.Dependent;
import com.cognizant.user.entity.Login;
import com.cognizant.user.entity.MemberDetail;
import com.cognizant.user.entity.User;
import com.cognizant.user.entity.UserDetail;
import com.cognizant.user.exception.DataMissingException;
import com.cognizant.user.exception.ResourceNotFoundException;
import com.cognizant.user.exception.UserNotFoundException;
import com.cognizant.user.service.UserService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/{id}")
	public ResponseEntity<?> getUserById(@PathVariable String id) {

		Optional<User> user = userService.getUserById(id);

		if (user.isEmpty()) {
			return new ResponseEntity<>("User Not found", HttpStatus.OK);
		}

		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@PostMapping("/register")
	public ResponseEntity<?> saveUser(@RequestBody User user) {

		if (user.getName() == null || user.getEmail() == null) {
			throw new DataMissingException("Mandatory data is missing");
		}

		userService.register(user);
		Map<String, String> message = new HashMap<>();
		message.put("message", "User Created successfully");
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody UserDetail userDetail) {
		Optional<User> existingUser = userService.getUserById(id);

		if (existingUser.isEmpty()) {
			throw new ResourceNotFoundException("Member not found with the id : " + id);
		}

		if (userDetail.getUser().getName() == null || userDetail.getUser().getEmail() == null) {
			throw new DataMissingException("Mandatory data is missing");
		}

		UserDetail updatedUserDetail = userService.save(userDetail);
		return new ResponseEntity<>(updatedUserDetail, HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody Login loginDetail) {
		log.info("Value for username {} . Value for password {}.", loginDetail.getUsername(),
				loginDetail.getPassword());
		UserDetail userDetail = userService.verifyUserDetail(loginDetail.getUsername(), loginDetail.getPassword());

		try {
			if (Objects.isNull(userDetail)) {
				throw new UserNotFoundException("Invalid Credentials");
			}

			return new ResponseEntity<>(userDetail, HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("check/claim/{id}")
	public MemberDetail checkUserClaimDetail(@PathVariable String id) {
		MemberDetail memberDetail = new MemberDetail();

		if (id.startsWith("R")) {
			Optional<User> user = userService.getUserById(id);
			if (user.isPresent()) {
				memberDetail.setMemberId(user.get().getId());
				memberDetail.setName(user.get().getName());
				memberDetail.setDateOfBirth(user.get().getDateOfBirth());
			}

			return memberDetail;
		} else {
			Optional<Dependent> dependent = userService.getDependentById(id);

			if (dependent.isPresent()) {
				memberDetail.setMemberId(dependent.get().getId());
				memberDetail.setName(dependent.get().getName());
				memberDetail.setDateOfBirth(dependent.get().getDateOfBirth());
			}

			return memberDetail;
		}
	}

}
