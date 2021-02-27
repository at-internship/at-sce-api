package com.agilethought.atsceapi.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
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
