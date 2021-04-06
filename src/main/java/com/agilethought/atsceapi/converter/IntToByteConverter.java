package com.agilethought.atsceapi.converter;

import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;

public class IntToByteConverter extends CustomConverter<Integer, Byte>{

	@Override
	public Byte convert(
			Integer source,
			Type<? extends Byte> destinationType,
			MappingContext mappingContext
	) {

		return source.byteValue();

	}
}