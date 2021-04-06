package com.agilethought.atsceapi.dto.history;

import com.agilethought.atsceapi.domain.FixedExpenses;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(description = "Template that saves new history info from a request")
public class NewHistoryRequest {

	@ApiModelProperty(
	        required = true,
            value = "States the type of client that will consume the api.\n" +
                    "1 = Custom Web\n2 = e-commerce\n3 = Android app\n4 = IOS app",
            allowableValues = "1, 2, 3, 4"
    )
    private Integer type;

	@ApiModelProperty(
	        required = true,
            value = "User identifier associated with this history",
            example = "604b7efc95a9a15168d7f753"
    )
    private String user_id;

	@ApiModelProperty(
	        required = true,
            value = "Set of fields that represent the fixed expenses of the user"
    )
    private FixedExpenses fixedExpenses;

	@ApiModelProperty(
	        required = true,
            example = "10"
    )
    private Integer totalHours;

	@ApiModelProperty(
	        required = true,
            example = "14"
    )
    private Integer totalDays;

	@ApiModelProperty(
	        required = true,
            example = "320.8"
    )
    private Double costDay;

	@ApiModelProperty(
	        required = true,
            example = "35.0"
    )
    private Double costHour;

	@ApiModelProperty(
	        required = true,
            example = "5637.3"
    )
    private Double projectCost;

	@ApiModelProperty(
	        required = true,
            example = "600.0"
    )
    private Double taxIVA;

	@ApiModelProperty(
	        required = true,
            example = "2328.9"
    )
    private Double taxISR_r;

	@ApiModelProperty(
	        required = true,
            example = "599.99"
    )
    private Double taxIVA_r;

	@ApiModelProperty(
	        required = true,
            example = "10000.0"
    )
    private Double total;

	@ApiModelProperty(
            required = true,
            example = "3588.3"
    )
    private Double revenue;

    @ApiModelProperty(
            value = "0 = unavailable\n1 = available",
            allowEmptyValue = true,
            example = "1",
            allowableValues = "0, 1"
    )
    private Integer status;

}
