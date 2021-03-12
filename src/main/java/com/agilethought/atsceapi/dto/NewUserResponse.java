package com.agilethought.atsceapi.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(description = "User Response")
public class NewUserResponse {
    @ApiModelProperty(example = "6fbd5", value = "Unique Identifier")
    private String id;
}