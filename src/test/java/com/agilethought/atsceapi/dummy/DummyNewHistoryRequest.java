package com.agilethought.atsceapi.dummy;

import com.agilethought.atsceapi.domain.FixedExpenses;
import com.agilethought.atsceapi.dto.history.NewHistoryRequest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DummyNewHistoryRequest extends NewHistoryRequest {
	public FixedExpenses fixedExpenses;
	public NewHistoryRequest newHistoryRequest;

	public DummyNewHistoryRequest() {
		super();
		this.fixedExpenses = new FixedExpenses();
		this.newHistoryRequest = new NewHistoryRequest();

	}

	public void populateHistoryRequest() {
		fixedExpenses.setRent(1.0);
		fixedExpenses.setTransport(1.0);
		fixedExpenses.setInternet(1.0);
		fixedExpenses.setFeed(1.0);
		fixedExpenses.setOthers(1.0);
		fixedExpenses.setTotal(5.0);

		newHistoryRequest.setType(1);
		newHistoryRequest.setUser_id("1");
		newHistoryRequest.setFixedExpenses(fixedExpenses);
		newHistoryRequest.setTotalHours(1);
		newHistoryRequest.setTotalDays(1);
		newHistoryRequest.setCostDay(1.0);
		newHistoryRequest.setCostHour(1.0);
		newHistoryRequest.setProjectCost(1.0);
		newHistoryRequest.setTaxIVA(1.0);
		newHistoryRequest.setTaxISR_r(1.0);
		newHistoryRequest.setTaxIVA_r(1.0);
		newHistoryRequest.setTotal(1.0);
		newHistoryRequest.setRevenue(1.0);
		newHistoryRequest.setStatus(1);
	}
}
