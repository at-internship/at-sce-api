package com.agilethought.atsceapi.dto;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UpdateUserResponse {
	//The Response will be a 200 HttpStatus.OK with the follow Response Body
	private String id;
	private Integer type;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Integer status; 
}
