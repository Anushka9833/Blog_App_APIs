package com.anushka.blogtest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anushka.blogtest.entities.Category;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
	
	

}
