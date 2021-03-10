package com.agilethought.atsceapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FixedExpensesDTO {
	private double rent;
	private double transport;
	private double internet;
	private double feed;
	private double others;
	private double total;
}