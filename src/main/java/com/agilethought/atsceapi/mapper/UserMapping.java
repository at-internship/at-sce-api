package com.agilethought.atsceapi.mapper;

import com.agilethought.atsceapi.dto.*;
import org.springframework.stereotype.Component;

import com.agilethought.atsceapi.model.User;

import ma.glasnost.orika.MapperFactory;
import net.rakugakibox.spring.boot.orika.OrikaMapperFactoryConfigurer;

@Component
public class UserMapping implements OrikaMapperFactoryConfigurer {

	@Override
	public void configure(MapperFactory orikaMapperFactory) {
		orikaMapperFactory.classMap(User.class, NewUserResponse.class).mapNulls(false).byDefault().register();
		orikaMapperFactory.classMap(UpdateUserRequest.class, User.class).mapNulls(false).byDefault().register();
		orikaMapperFactory.classMap(User.class, UserDTO.class).mapNulls(false).byDefault().register();
		orikaMapperFactory.classMap(UserDTO.class, User.class).mapNulls(false).byDefault().register();
		orikaMapperFactory.classMap(User.class, UpdateUserResponse.class).mapNulls(false).byDefault().register();
		orikaMapperFactory.classMap(User.class, LoginDataResponse.class).mapNulls(false).byDefault().register();
	}

}