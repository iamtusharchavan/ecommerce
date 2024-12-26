package com.hefshine.amazon.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hefshine.amazon.model.Product;
import com.hefshine.amazon.projection.Productui;
import com.hefshine.amazon.repo.ProductRepo;

@RestController
@CrossOrigin
@RequestMapping("seller")
public class SellerController {
	
	@Autowired
	ProductRepo productRepo;
	
	
	@RequestMapping("getAllProducts{userid}")
	public List<Productui> getAllProducts(@PathVariable int userid){	
	List<Productui> test1 = productRepo.getCatNameById(userid);
	System.out.println(test1);
	return productRepo.getCatNameById(userid);
	}
	
	@RequestMapping("addNewProduct")
	public Product addNewProduct(@RequestBody Product obj) {
		obj.setDate(new Date()); 
		return productRepo.save(obj);
	}

}
