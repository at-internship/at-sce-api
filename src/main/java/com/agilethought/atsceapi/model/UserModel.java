package com.agilethought.atsceapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "USERS")
public class UserModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@Column(name="TYPE")
	private int type;
	
	@NotNull
	@Column(name="FIRST_NAME")
	private String fistName;
	
	@NotNull
	@Column(name="LAST_NAME")
	private String lastName;
	
	@NotNull
	@Column(name="EMAIL")
	private String email;
	
	@NotNull
	@Column(name="PASSWORD")
	private String password;
	
	@NotNull
	@Column(name="STATUS")
	private int status;
} 