package com.agilethought.atsceapi.mapper;

import org.springframework.stereotype.Component;

import com.agilethought.atsceapi.dto.NewUserResponse;
import com.agilethought.atsceapi.dto.UpdateUserResponse;
import com.agilethought.atsceapi.dto.UserDTO;
import com.agilethought.atsceapi.dto.UserRequest;
import com.agilethought.atsceapi.model.User;

import ma.glasnost.orika.MapperFactory;
import net.rakugakibox.spring.boot.orika.OrikaMapperFactoryConfigurer;

@Component
public class UserMapping implements OrikaMapperFactoryConfigurer {

	@Override
	public void configure(MapperFactory orikaMapperFactory) {
		orikaMapperFactory.classMap(User.class, NewUserResponse.class).mapNulls(false).byDefault().register();
		orikaMapperFactory.classMap(UserRequest.class, User.class).mapNulls(false).byDefault().register();
		orikaMapperFactory.classMap(User.class, UserDTO.class).mapNulls(false).byDefault().register();
		orikaMapperFactory.classMap(UserDTO.class, User.class).mapNulls(false).byDefault().register();
		orikaMapperFactory.classMap(User.class, UpdateUserResponse.class).mapNulls(false).byDefault().register();
	}

}