package com.agilethought.atsceapi.converter;

import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;

public class BoolToIntegerConverter extends CustomConverter<Boolean, Integer> {
	
	@Override
	public Integer convert(
			Boolean source,
			Type<? extends Integer> destinationType,
			MappingContext mappingContext
	) {

		return source ? 1 : 0;

	}
}