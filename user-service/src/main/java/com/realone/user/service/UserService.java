package com.realone.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.realone.realonemodel.model.users.User;
import com.realone.realonemodel.repository.users.UserRepository;



@Service
public class UserService {

	
	//private UserRepository userRepository;
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void saveUserDetails(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}

	public void updateUserDetails(User user) {
		User existingUser = userRepository.findById(user.getId())
				.orElseThrow(()-> new RuntimeException(user.getUserId() + "not found"));
		
		if(existingUser != null) {
			userRepository.save(user);
		}
	}

	public List<User> getAllUsers() {
		
		return userRepository.findAll();
	}

	public User getUserById(Long id) {
		return userRepository.findById(id).orElseThrow(()-> new RuntimeException("user not found"));
	}

	public void deleteUserById(Long id) {
		userRepository.deleteById(id);
	}

}
