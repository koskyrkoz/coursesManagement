package com.example.coursesmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.coursesmanagement.domain.User;
import com.example.coursesmanagement.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/register")
	public String getRegisterPage(Model model) {
		model.addAttribute("registerRequest", new User());
		return "register_page";
	}
	
	@GetMapping("/enter")
	public String getLoginPage(Model model) {
		model.addAttribute("loginRequest", new User());
		return "menu";
	}
	
	
	@PostMapping("/login")
	public String login(@ModelAttribute ("loginRequest") User user) {
		
		User validateUsr = userService.authenticate(user.getUsername(), user.getPassword());
		
		if (validateUsr != null)
			return "success_login";
		else
			return "redirect:/enter";
	}
	
	
	@PostMapping("/add_new_user")
	public String register(@ModelAttribute ("registerRequest") User user) {
		
		User registeredUser = userService.registerUser(user.getUsername(), user.getPassword());
		
		if (registeredUser == null)
			return "error_page";
		else
			return "menu";
	}
	
}
