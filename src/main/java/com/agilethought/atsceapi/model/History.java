package com.agilethought.atsceapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.agilethought.atsceapi.domain.FixedExpenses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Document(collection = "histories")
public class History {
	@Id
	private String id;
	private Byte type;
	private String user_id;
	private FixedExpenses fixedExpenses;
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
	private Boolean status;
}
