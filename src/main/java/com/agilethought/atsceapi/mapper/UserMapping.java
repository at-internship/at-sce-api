package com.agilethought.atsceapi.mapper;

import com.agilethought.atsceapi.dto.user.LoginResponse;
import com.agilethought.atsceapi.dto.user.NewUserResponse;
import com.agilethought.atsceapi.dto.user.UpdateUserResponse;
import com.agilethought.atsceapi.dto.user.UserDTO;
import com.agilethought.atsceapi.model.User;
import org.springframework.stereotype.Component;

import ma.glasnost.orika.MapperFactory;
import net.rakugakibox.spring.boot.orika.OrikaMapperFactoryConfigurer;

@Component
public class UserMapping implements OrikaMapperFactoryConfigurer {

	@Override
	public void configure(MapperFactory orikaMapperFactory) {
		orikaMapperFactory.classMap(com.agilethought.atsceapi.model.User.class, NewUserResponse.class).mapNulls(false).byDefault().register();
		orikaMapperFactory.classMap(User.class, com.agilethought.atsceapi.model.User.class).mapNulls(false).byDefault().register();
		orikaMapperFactory.classMap(com.agilethought.atsceapi.model.User.class, UserDTO.class).mapNulls(false).byDefault().register();
		orikaMapperFactory.classMap(UserDTO.class, com.agilethought.atsceapi.model.User.class).mapNulls(false).byDefault().register();
		orikaMapperFactory.classMap(com.agilethought.atsceapi.model.User.class, UpdateUserResponse.class).mapNulls(false).byDefault().register();
		orikaMapperFactory.classMap(com.agilethought.atsceapi.model.User.class, LoginResponse.class).mapNulls(false).byDefault().register();
	}

}