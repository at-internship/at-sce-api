package com.agilethought.atsceapi.dto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(description = "User Model")
public class UserDTO {
	@ApiModelProperty(example = "6fbd5", value = "Unique Identifier")
	private String id;
	@ApiModelProperty(example = "2", value = "Value that allows to identify if the user is admin or normal user")
	private Integer type;
	@ApiModelProperty(example = "Juan", value = "First name of the user")
	private String firstName;
	@ApiModelProperty(example = "Pérez", value = "Last name of the user")
	private String lastName;
	@ApiModelProperty(example = "juan.perez@agile.thought.com", value = "User's email that allows us to log in the application, has to be unique")
	private String email;
	@ApiModelProperty(example = "1", value = "Value that allows to identify if the user is active or inactive")
	private Integer status;
}