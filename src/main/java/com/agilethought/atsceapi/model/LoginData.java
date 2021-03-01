package com.agilethought.atsceapi.model;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LoginData {

     @NotBlank
     private String email;

     @NotNull
     private String password;
}
