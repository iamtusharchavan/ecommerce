package com.hefshine.amazon.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hefshine.amazon.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	
	int countByUsername(String username);
	User findByUsername(String username);

}
