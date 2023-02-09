package com.anushka.blogtest.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.anushka.blogtest.payloads.UserDto;


public interface UserService {
	
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user,Integer userId);
	UserDto getUserByID(Integer userId);
	List<UserDto> getAllUsers();
	void deleteUser(Integer userId);

}
