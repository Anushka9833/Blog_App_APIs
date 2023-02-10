package com.anushka.blogtest.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anushka.blogtest.entities.Post;
import com.anushka.blogtest.entities.User;
import com.anushka.blogtest.entities.Category;
import com.anushka.blogtest.exceptions.ResourceNotFoundException;
import com.anushka.blogtest.payloads.PostDto;
import com.anushka.blogtest.repositories.CategoryRepo;
import com.anushka.blogtest.repositories.PostRepo;
import com.anushka.blogtest.repositories.UserRepo;
import com.anushka.blogtest.services.PostService;

@Service
public class PostServiceImpl implements PostService {
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user"," user id", userId));
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category"," caegoryId",categoryId));
		
		
		Post post=this.modelMapper.map(postDto,Post.class);
		post.setImageName("default.png");
		post.setDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		Post updatedPost=this.postRepo.save(post);
		
		return this.modelMapper.map(updatedPost,PostDto.class);
		
		
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post"," post id ",postId));
		post.setPostTitle(postDto.getPostTitle());
		post.setPostContent(postDto.getPostContent());
		post.setImageName(postDto.getImageName());
		Post updatedPost=this.postRepo.save(post);
		
		return modelMapper.map(updatedPost,PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post"," post id ",postId));
		this.postRepo.delete(post);	
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post"," post id ",postId));
		return this.modelMapper.map(post,PostDto.class);
	}

	@Override
	public List<PostDto> getAllPosts() {
		List<Post> posts=this.postRepo.findAll();
		List<PostDto> postDtos=posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> getAllPostsByCategory(Integer categoryId) {
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category"," cataegory id",categoryId));
		List<Post> posts=this.postRepo.findByCategory(cat);
		
		List<PostDto> postDtos=posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> getAllPostsByUser(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user"," user id",userId));
		List<Post> posts=this.postRepo.findByUser(user);
		
		List<PostDto> postDtos=posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
