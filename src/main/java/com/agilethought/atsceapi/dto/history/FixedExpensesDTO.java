package com.agilethought.atsceapi.dto.history;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FixedExpensesDTO {

	private Double rent;
	private Double transport;
	private Double internet;
	private Double feed;
	private Double others;
	private Double total;

}