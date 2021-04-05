package com.agilethought.atsceapi.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(description = "Template that saves fixed expenses")
public class FixedExpenses {
	@ApiModelProperty(example = "6000.0")
    private Double rent;
	@ApiModelProperty(example = "80.0")
    private Double transport;
	@ApiModelProperty(example = "349.0")
    private Double internet;
	@ApiModelProperty(example = "1200.99")
    private Double feed;
	@ApiModelProperty(example = "200.0")
    private Double others;
	@ApiModelProperty(example = "7829.99")
    private Double total;
}