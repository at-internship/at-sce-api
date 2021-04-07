package com.agilethought.atsceapi.population;

import com.agilethought.atsceapi.model.History;

import java.time.LocalDate;

public class HistoryPopulation {
    public static void populate(History history) {
        LocalDate today = LocalDate.now();
        history.setCreationDate(today);
    }
}
