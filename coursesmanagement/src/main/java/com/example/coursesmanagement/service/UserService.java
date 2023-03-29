package com.example.coursesmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.coursesmanagement.domain.User;
import com.example.coursesmanagement.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	public UserService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	public User registerUser(String username, String password) {
		if (username != null && password != null) {
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			
			return userRepo.save(user);
		}else
			return null;
	}
	
	public User authenticate(String username, String password) {
		return userRepo.findByUsernameAndPassword(username, password);
	}
}
