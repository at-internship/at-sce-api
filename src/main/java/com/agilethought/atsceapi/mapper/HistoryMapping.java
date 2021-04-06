package com.agilethought.atsceapi.mapper;

import com.agilethought.atsceapi.dto.history.FixedExpensesDTO;
import com.agilethought.atsceapi.dto.history.HistoryDTO;
import com.agilethought.atsceapi.dto.history.NewHistoryRequest;
import com.agilethought.atsceapi.dto.history.NewHistoryResponse;
import org.springframework.stereotype.Component;
import com.agilethought.atsceapi.converter.*;
import com.agilethought.atsceapi.domain.FixedExpenses;
import com.agilethought.atsceapi.model.History;
import ma.glasnost.orika.MapperFactory;
import net.rakugakibox.spring.boot.orika.OrikaMapperFactoryConfigurer;

@Component
public class HistoryMapping implements OrikaMapperFactoryConfigurer {

	private static final String TYPE = "type";
	private static final String STATUS = "status";
	private static final String INT_TO_BOOLEAN = "intToBoolean";
	private static final String INT_TO_BYTE = "intToByte";
	private static final String BOOL_TO_INTEGER = "boolToInteger";
	private static final String BYTE_TO_INTEGER = "byteToInteger";
	
	@Override
	public void configure(MapperFactory orikaMapperFactory) {
		orikaMapperFactory.getConverterFactory().registerConverter(INT_TO_BOOLEAN, new IntToBooleanConverter());
		orikaMapperFactory.getConverterFactory().registerConverter(INT_TO_BYTE, new IntToByteConverter());
		orikaMapperFactory.getConverterFactory().registerConverter(BOOL_TO_INTEGER, new BoolToIntegerConverter());
		orikaMapperFactory.getConverterFactory().registerConverter(BYTE_TO_INTEGER, new ByteToIntegerConverter());
		orikaMapperFactory.classMap(FixedExpenses.class, FixedExpensesDTO.class).mapNulls(false).byDefault().register();
		orikaMapperFactory.classMap(FixedExpensesDTO.class, FixedExpenses.class).mapNulls(false).byDefault().register();
		orikaMapperFactory.classMap(HistoryDTO.class, History.class)
				.fieldMap(TYPE, TYPE).converter(INT_TO_BYTE).add()
				.fieldMap(STATUS, STATUS).converter(INT_TO_BOOLEAN).add()
				.mapNulls(false).byDefault().register();
		orikaMapperFactory.classMap(History.class, HistoryDTO.class)
				.fieldMap(TYPE, TYPE).converter(BYTE_TO_INTEGER).add()		
				.fieldMap(STATUS, STATUS).converter(BOOL_TO_INTEGER).add()
				.mapNulls(false).byDefault().register();	
		orikaMapperFactory.classMap(History.class, NewHistoryResponse.class).mapNulls(false).byDefault().register();
		orikaMapperFactory.classMap(NewHistoryRequest.class, History.class)
				.fieldMap(STATUS, STATUS).converter(INT_TO_BOOLEAN).add()
				.fieldMap(TYPE,TYPE).converter(INT_TO_BYTE).add()
				.mapNulls(false).byDefault().register();
	}
}