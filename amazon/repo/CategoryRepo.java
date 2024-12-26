package com.hefshine.amazon.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hefshine.amazon.model.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
	

	

}
