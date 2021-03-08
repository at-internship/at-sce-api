package com.agilethought.atsceapi.domain;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
	private String id;
	private Integer type;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Integer status;
}