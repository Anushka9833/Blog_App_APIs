package com.anushka.blogtest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anushka.blogtest.entities.Post;
import com.anushka.blogtest.payloads.ApiResponse;
import com.anushka.blogtest.payloads.PostDto;
import com.anushka.blogtest.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {
	@Autowired
	private PostService postService;
	//create
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,@PathVariable("userId") Integer userId,@PathVariable("categoryId") Integer categoryId){
		PostDto post=this.postService.createPost(postDto,userId,categoryId);
		return new ResponseEntity<PostDto>(post,HttpStatus.CREATED);
	}
	
	
	//get by user
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getAllPostsByUser(@PathVariable("userId") Integer userId){
		List<PostDto> postDtos=this.postService.getAllPostsByUser(userId);
		return  new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK);
	}
	
	//get by category
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getAllPostsByCategory(@PathVariable("categoryId") Integer categoryId){
		List<PostDto> postDtos=this.postService.getAllPostsByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK);
	}

	
	//get all posts
	@GetMapping("/posts")
	public ResponseEntity<List<PostDto>> getAllPosts(){
		List<PostDto> postDtos=this.postService.getAllPosts();
		return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK);
	}
	
	//get post by id
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable("postId") Integer postId){
		PostDto postDto=this.postService.getPostById(postId);
		return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
	}
	
	//delete post by id
	@DeleteMapping("/posts/{postId}")
	public ApiResponse deletePostById(@PathVariable("postId") Integer postId){
		this.postService.deletePost(postId);
		return new ApiResponse("Post is successfully deleted",true);
		
	}
	
	//update post by id
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePostById(@RequestBody PostDto postDto,@PathVariable Integer postId){
		PostDto updatePost=this.postService.updatePost(postDto,postId);
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
		
	}
	
	
	
}
