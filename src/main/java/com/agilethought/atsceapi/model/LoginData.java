package com.agilethought.atsceapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class LoginData {
     @NotBlank
     private String email;
     @NotNull
     @Size(min = 2, max = 6)
     private String password;


}
