package com.agilethought.atsceapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "users")
public class Users {
	@Id
	private String id;
	private Integer type;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Integer status;
	
}
