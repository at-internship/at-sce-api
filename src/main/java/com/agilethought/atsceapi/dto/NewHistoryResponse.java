package com.agilethought.atsceapi.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@ApiModel(description="Template that saves history info for a response")
public class NewHistoryResponse {
	@ApiModelProperty(value = "Unique identifier", example = "604bd54e9fb3ee3c9827408f")
    private String id;
}
