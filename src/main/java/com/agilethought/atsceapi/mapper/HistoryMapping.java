package com.agilethought.atsceapi.mapper;

import org.springframework.stereotype.Component;

import com.agilethought.atsceapi.converter.IntToBooleanConverter;
import com.agilethought.atsceapi.domain.FixedExpenses;
import com.agilethought.atsceapi.dto.FixedExpensesDTO;
import com.agilethought.atsceapi.dto.HistoryDTO;
import com.agilethought.atsceapi.model.History;

import ma.glasnost.orika.MapperFactory;
import net.rakugakibox.spring.boot.orika.OrikaMapperFactoryConfigurer;

@Component
public class HistoryMapping implements OrikaMapperFactoryConfigurer {

	@Override
	public void configure(MapperFactory orikaMapperFactory) {
		orikaMapperFactory.getConverterFactory().registerConverter("intToBoolean", new IntToBooleanConverter());

		orikaMapperFactory.classMap(FixedExpenses.class, FixedExpensesDTO.class).mapNulls(false).byDefault().register();
		orikaMapperFactory.classMap(FixedExpensesDTO.class, FixedExpenses.class).mapNulls(false).byDefault().register();
		orikaMapperFactory.classMap(History.class, HistoryDTO.class).mapNulls(false).byDefault().register();
		orikaMapperFactory.classMap(HistoryDTO.class, History.class).fieldMap("status", "status")
				.converter("intToBoolean").add().mapNulls(false).byDefault().register();
	}

}