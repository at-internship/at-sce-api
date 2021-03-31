package com.agilethought.atsceapi.converter;

import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;

public class IntToBooleanConverter extends CustomConverter<Integer, Boolean> {
	
	@Override
	public Boolean convert(Integer source, Type<? extends Boolean> destinationType, MappingContext mappingContext) {
		if (source > 0)
			return true;
		else
			return false;
	}
}