package com.agilethought.atsceapi.dto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(description = "New User Request")
public class NewUserRequest {
	@ApiModelProperty(example = "2", value = "Value that allows to identify if the user is admin or normal user")
	private Integer type;
	@ApiModelProperty(example = "Juan", value = "First name of the user")
	private String firstName;
	@ApiModelProperty(example = "Perez", value = "Last name of the user")
	private String lastName;
	@ApiModelProperty(example = "juan.perez@agilethought.com", value = "User's email that allows us to log in the application, has to be unique")
	private String email;
	@ApiModelProperty(example = "jp085b3e", value = "Secret Code to enter to the email")
	private String password;
	@ApiModelProperty(example = "1", value = "Value that allows to identify if the user is active or inactive")
	private Integer status;
}