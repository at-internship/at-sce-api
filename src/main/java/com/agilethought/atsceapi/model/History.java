package com.agilethought.atsceapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.agilethought.atsceapi.domain.FixedExpenses;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;

import java.time.LocalDate;

@Data
@Document(collection = "histories")
@ApiModel(description = "Template that saves history info")
public class History {

	@Id
	@ApiModelProperty(
			value = "History identifier generated by MongoDB",
			example = "604bd54e9fb3ee3c9827408f"
	)
	private String id;

	@ApiModelProperty(
			required = true,
			value = "States the type of client that will consume the api.\n" +
					"1 = Custom Web\n2 = e-commerce\n3 = Android app\n4 = IOS app",
			allowableValues = "1, 2, 3, 4"
	)
	private Byte type;

	@ApiModelProperty(
			value = "User identifier associated with this history",
			example = "604b7efc95a9a15168d7f753"
	)
	private String user_id;

	@ApiModelProperty(value = "Set of fields that represent the fixed expenses of the user")
	private FixedExpenses fixedExpenses;

	@ApiModelProperty(example = "10")
	private Integer totalHours;

	@ApiModelProperty(example = "14")
	private Integer totalDays;

	@ApiModelProperty(example = "320.8")
	private Double costDay;

	@ApiModelProperty(example = "35.0")
	private Double costHour;

	@ApiModelProperty(example = "5637.3")
	private Double projectCost;

	@ApiModelProperty(example = "600.0")
	private Double taxIVA;

	@ApiModelProperty(example = "2328.9")
	private Double taxISR_r;

	@ApiModelProperty(example = "599.99")
	private Double taxIVA_r;

	@ApiModelProperty(example = "10000.0")
	private Double total;

	@ApiModelProperty(example = "3588.3")
	private Double revenue;

	@ApiModelProperty(
			allowEmptyValue = true,
			example = "false",
			allowableValues = "true, false"
	)
	private Boolean status;
	private LocalDate creationDate;

}