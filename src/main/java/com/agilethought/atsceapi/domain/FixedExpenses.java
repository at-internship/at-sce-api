package com.agilethought.atsceapi.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FixedExpenses {
    private Double rent;
    private Double transport;
    private Double internet;
    private Double feed;
    private Double others;
    private Double total;
}
