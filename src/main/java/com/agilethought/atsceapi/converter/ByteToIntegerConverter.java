package com.agilethought.atsceapi.converter;

import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;

public class ByteToIntegerConverter extends CustomConverter<Byte, Integer> {
	
	@Override
	public Integer convert(
			Byte source,
			Type<? extends Integer> destinationType,
			MappingContext mappingContext
	) {

		return source.intValue();

	}	
}