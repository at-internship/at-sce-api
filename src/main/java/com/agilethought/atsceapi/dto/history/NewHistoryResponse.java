package com.agilethought.atsceapi.dto.history;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(description="Template that saves history info for a response")
public class NewHistoryResponse {

	@ApiModelProperty(
	        value = "Unique identifier",
            example = "604bd54e9fb3ee3c9827408f"
    )
    private String id;

}
