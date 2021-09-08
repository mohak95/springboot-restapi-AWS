package com.javaguides.springbootcrud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaguides.springbootcrud.entity.User;
import com.javaguides.springbootcrud.exception.ResourceNotFoundException;
import com.javaguides.springbootcrud.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserRepository userRepo;
	// get all users

	@GetMapping
	public List<User> getAllUsers() {
		
		return userRepo.findAll();
	}
	
	//get user by id
	@GetMapping("/{id}")
	public User getUserById(@PathVariable(value = "id") int userId) {
		
		return this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userId));
	}
	
	
	//create user
	@PostMapping
	public User createUser(@RequestBody User user) {
		return this.userRepo.save(user);
	}
	
	
	//update user
	@PostMapping("/{id}")
	public User updateUser(@RequestBody User user, @PathVariable("id") int userId) {
		
		User existingUser =  this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userId));

		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());
		return this.userRepo.save(existingUser);
	}
	
	
	//delete user by id
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable("id") int userId) {
		
		User existingUser =  this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userId));

		this.userRepo.delete(existingUser);
		return ResponseEntity.ok().build();
	}
	
	
}
