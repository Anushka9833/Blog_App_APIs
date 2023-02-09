package com.anushka.blogtest.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.anushka.blogtest.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}
