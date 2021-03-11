package com.agilethought.atsceapi.model;

import com.agilethought.atsceapi.domain.FixedExpenses;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(collection = "histories")
public class History {
    @Id
    private String id;
    private byte type;
    private String user_id;
    private FixedExpenses fixedExpenses;
    private int totalHours;
    private int totalDays;
    private double costDay;
    private double costHour;
    private double taxIVA;
    private double taxISR_r;
    private double taxIVA_r;
    private double total;
    private double revenue;
    private boolean status;
}
