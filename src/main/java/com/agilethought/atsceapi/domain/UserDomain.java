package com.agilethought.atsceapi.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class UserDomain{
	private int id;
	private int type;
	private String fistName;
	private String lastName;
	private String email;
	private String password;
	private int status;
}