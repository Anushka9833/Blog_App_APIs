package com.anushka.blogtest.services;

import com.anushka.blogtest.payloads.CommentDto;

public interface CommentService {

	public CommentDto createComment(CommentDto commentDto,Integer postId);
	public void deleteComment(Integer commentId);
}
