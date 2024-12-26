package com.hefshine.amazon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hefshine.amazon.model.LoginReturn;
import com.hefshine.amazon.model.User;
import com.hefshine.amazon.repo.UserRepo;

@RestController
@CrossOrigin
@RequestMapping("login")
public class LoginController {
	
	@Autowired
	UserRepo userRepo;
	
	@RequestMapping("getName{id}")
	public String[] getName(@PathVariable int id) {
		User user = userRepo.findById(id).get();
		String[] sa= new String[1];
		sa[0]=user.getName();
		return sa;
	}
	
	@RequestMapping("log")
	public LoginReturn loginReturn(@RequestBody String[] sa) {
		
		if(sa==null)
			return new LoginReturn(-1, -1, "Enter usernamePassword");
		
		String username=sa[0];
		if(username==null || username.length()<1)
			return new LoginReturn(-1, -1, "Enter Username");
		
		String password=sa[1];
		if(password==null || password.length()<1)
			return new LoginReturn(-1, -1, "Enter Password");
		
		int count = userRepo.countByUsername(username);
		
		if(count==0)
			return new LoginReturn(-1, -1, "Username is wrong");
		
		if(count>1)
			return new LoginReturn(-1, -1, "Something is wrong with username");
		
		User user = userRepo.findByUsername(username);
		
		if(user.getPassword().equals(password)) {
			return new LoginReturn(user.getId(), user.getAccountType(), null);
		}
		else {
			return new LoginReturn(-1, -1, "passwordWrong");
		}		
	}
}
