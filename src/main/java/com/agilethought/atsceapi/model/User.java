package com.agilethought.atsceapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import lombok.Data;

@Data
@Document(collection = "user")
public class User {

	@Id
	private String id;
	private Integer type;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Integer status;

}
