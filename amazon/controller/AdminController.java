package com.hefshine.amazon.controller;

import java.util.List;
import java.util.Date;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hefshine.amazon.model.Category;
import com.hefshine.amazon.repo.CategoryRepo;

@RestController
@CrossOrigin
@RequestMapping("admin")
public class AdminController {
	
	@Autowired
	CategoryRepo categoryRepo;

	@RequestMapping("getAllCategories")
	public List<Category> getAllCategories(){
		return categoryRepo.findAll();
	}
	
	@RequestMapping("addNewCategory{userid}")
	public Category addNewCategory(@PathVariable int userid, @RequestBody String name) {
		Category c = new Category();
		c.setDate(new Date());
		c.setName(name);
		c.setUserid(userid);
		return categoryRepo.save(c);
	}
	
}
