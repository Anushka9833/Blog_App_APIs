package com.anushka.blogtest.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	private int id;
	
	@NotEmpty
	@Size(min=4,message="username must be minimum of 4 characters")
	private String username;
	
	@Email(message="email address is not valid")
	private String email;
	
	@NotEmpty
	@Size(min=3,max=10,message="password must be minimum of 3 chars and maximum of 10 chars")
	private String password;
	
	@NotEmpty(message="about cannot be blank")
	private String about;
	

}
