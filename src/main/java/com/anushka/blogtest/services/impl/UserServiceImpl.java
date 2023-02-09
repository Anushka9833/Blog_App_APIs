package com.anushka.blogtest.services.impl;

import java.util.List;

import com.anushka.blogtest.payloads.UserDto;
import com.anushka.blogtest.repositories.UserRepo;
import com.anushka.blogtest.services.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anushka.blogtest.entities.*;
import com.anushka.blogtest.exceptions.*;
import java.util.stream.*;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		User user=this.dtoToUser(userDto);
		User savedUser=this.userRepo.save(user);
		
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User"," id ",userId));
		user.setUsername(userDto.getUsername());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updatedUser=this.userRepo.save(user);
		return this.userToDto(updatedUser);
		
	}

	@Override
	public UserDto getUserByID(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User"," id ",userId));
		return this.userToDto(user);
		
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users=this.userRepo.findAll();
		//List<UserDto> userDtos=users.stream().map(user->this.userToDto(user)).collect(COllectors.toList());
		List<UserDto> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User"," id ",userId));
		this.userRepo.delete(user);

	}
	
//	private User dtoToUser(UserDto userDto) {
//		User user=new User();
//		user.setId(userDto.getId());
//		user.setUsername(userDto.getUsername());
//		user.setEmail(userDto.getEmail());
//		user.setAbout(userDto.getAbout());
//		user.setPassword(userDto.getPassword());
//		return user;
//		
//	}
//	
//	private UserDto userToDto(User user){
//		UserDto userDto=new UserDto();
//		userDto.setId(user.getId());
//		userDto.setUsername(user.getUsername());
//		userDto.setEmail(user.getEmail());
//		userDto.setAbout(user.getAbout());
//		userDto.setPassword(user.getPassword());
//		return userDto;
//	}
	
	private User dtoToUser(UserDto userDto) {
		User user=this.modelMapper.map(userDto,User.class);
		return user;
	}
	
	private UserDto userToDto(User user){
		UserDto userDto=this.modelMapper.map(user,UserDto.class);
		return userDto;
		
	}

}
