package com.realone.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realone.realonemodel.model.users.User;
import com.realone.user.service.UserService;

@RestController
@RequestMapping("/user_service")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@GetMapping("/welcome")
	public String welcome() {
		return "This is a welcome page";
	}
	
	
	@PostMapping("/create_user")
	//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<?> createUser(@RequestBody User user){
		try {
			userService.saveUserDetails(user);
			return new ResponseEntity<>("created", HttpStatus.OK);
		} catch (Exception e) {
			logger.error("error in creating user: " + user.getUserId() , e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/update_user")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<?> updateUser(@RequestBody User user){
		try {
			userService.updateUserDetails(user);
			return new ResponseEntity<>("updated", HttpStatus.OK);
		} catch (Exception e) {
			logger.error("error in updating user: " + user.getUserId() , e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/users")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<?> getAllUsers(){
		try {
			return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("error in getting users: ", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/user/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<?> getUserById(@PathVariable Long id){
		try {
			return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("error in getting user : ", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id){
		try {
			userService.deleteUserById(id);
			return new ResponseEntity<>("deleted", HttpStatus.OK);
		} catch (Exception e) {
			logger.error("error in deleting user : ", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
}
