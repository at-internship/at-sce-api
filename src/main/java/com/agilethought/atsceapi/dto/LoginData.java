package com.agilethought.atsceapi.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(description = "Template that saves temporary user credentials")
public class LoginData {

    @ApiModelProperty(required = true, example = "juan.perez@agilethought.com", value = "User's email that allows us to log in the application, has to be unique")
    private String email;
    @ApiModelProperty(required = true, example = "jp087gadf0", value = "Secret code to enter to the email")
    private String password;
}