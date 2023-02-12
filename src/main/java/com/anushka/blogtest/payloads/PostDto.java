package com.anushka.blogtest.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Locale.Category;
import java.util.Set;

import com.anushka.blogtest.entities.Comment;
import com.anushka.blogtest.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
	private Integer postId;
	private String postTitle;
	private String postContent;
	private String imageName;
	private Date Date;
	private CategoryDto category;
	private UserDto user;
	private Set<CommentDto> comments=new HashSet<>();
}
