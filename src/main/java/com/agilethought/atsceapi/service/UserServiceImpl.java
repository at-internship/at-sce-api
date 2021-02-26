package com.agilethought.atsceapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.agilethought.atsceapi.model.UserModel;
import com.agilethought.atsceapi.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public List <UserModel> getAllUsers(){
		List <UserModel> userModel = userRepository.findAll();
		return userModel;
	}
}
