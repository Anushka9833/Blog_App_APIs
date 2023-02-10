package com.anushka.blogtest.services;

import java.util.List;

import com.anushka.blogtest.entities.Post;
import com.anushka.blogtest.payloads.PostDto;

public interface PostService {
	
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	PostDto updatePost(PostDto postDto,Integer postId);
	void deletePost(Integer postId);
	PostDto getPostById(Integer postId);
	List<PostDto> getAllPosts();
	List<PostDto> getAllPostsByCategory(Integer categoryId);
	List<PostDto> getAllPostsByUser(Integer userId);
	List<PostDto> searchPosts(String keyword);

}
