package com.agilethought.atsceapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HistoryDTO {
	private String id;
	private int type;
	private String user_id;
	private FixedExpensesDTO fixedExpenses;
	private int totalHours;
	private int totalDays;
	private double costDay;
	private double costHour;
	private double projectCost;
	private double taxIVA;
	private double taxISR_r;
	private double taxIVA_r;
	private double total;
	private double revenue;
	private boolean status;
}