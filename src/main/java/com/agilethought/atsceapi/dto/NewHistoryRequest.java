package com.agilethought.atsceapi.dto;

import com.agilethought.atsceapi.domain.FixedExpenses;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewHistoryRequest {
    private Integer type;
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
    private Integer status;
}
