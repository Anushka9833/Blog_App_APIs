package com.anushka.blogtest.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anushka.blogtest.entities.Category;
import com.anushka.blogtest.entities.Post;
import com.anushka.blogtest.entities.User;

public interface PostRepo extends JpaRepository<Post,Integer> {
	
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	
	List<Post> findByPostTitleContaining(String title);
	

}
