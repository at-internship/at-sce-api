package com.agilethought.atsceapi.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
	private Integer type;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Integer status;
}