package com.agilethought.atsceapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "histories")
public class History {

	@Id
	private String id;
	private int type;
	private String user_id;
	private FixedExpenses fixedExpenses;
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
	private int status;
}
