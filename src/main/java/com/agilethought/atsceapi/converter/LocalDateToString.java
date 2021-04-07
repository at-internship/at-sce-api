package com.agilethought.atsceapi.converter;

import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateToString extends CustomConverter<LocalDate, String> {

    @Override
    public String convert(LocalDate source, Type<? extends String> type, MappingContext mappingContext) {
        String pattern = "MM-dd-yyyy";
        return source.format(DateTimeFormatter.ofPattern(pattern));
    }
}
