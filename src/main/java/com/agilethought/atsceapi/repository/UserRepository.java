package com.agilethought.atsceapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.agilethought.atsceapi.model.UserModel;

@Repository
public interface UserRepository extends MongoRepository<UserModel, String>{
	List<UserModel> findAll();
}
