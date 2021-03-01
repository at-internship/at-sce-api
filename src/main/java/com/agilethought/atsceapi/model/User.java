package com.agilethought.atsceapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "users")
public class User {

	@Id
	private String id;
	private int type;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private int status;

}
