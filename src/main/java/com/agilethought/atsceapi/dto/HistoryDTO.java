package com.agilethought.atsceapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HistoryDTO {
	private String id;
	private Integer type;
	private String user_id;
	private FixedExpensesDTO fixedExpenses;
	private Integer totalHours;
	private Integer totalDays;
	private Double costDay;
	private Double costHour;
	private Double projectCost;
	private Double taxIVA;
	private Double taxISR_r;
	private Double taxIVA_r;
	private Double total;
	private Double revenue;
	private Integer status;
}