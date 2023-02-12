package com.anushka.blogtest.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anushka.blogtest.entities.Comment;
import com.anushka.blogtest.entities.Post;
import com.anushka.blogtest.exceptions.ResourceNotFoundException;
import com.anushka.blogtest.payloads.CommentDto;
import com.anushka.blogtest.repositories.CommentRepo;
import com.anushka.blogtest.repositories.PostRepo;
import com.anushka.blogtest.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private CommentRepo commentRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post"," post id ",postId));
		Comment comment=this.modelMapper.map(commentDto,Comment.class);
		comment.setPost(post);
		Comment savedComment=this.commentRepo.save(comment);
		return this.modelMapper.map(savedComment,CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment com=this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment"," comment id",commentId));
		this.commentRepo.delete(com);
		
	}

}
