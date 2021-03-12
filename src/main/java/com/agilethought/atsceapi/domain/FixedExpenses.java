package com.agilethought.atsceapi.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FixedExpenses {
    private double rent;
    private double transport;
    private double internet;
    private double feed;
    private double others;
    private double total;
}
