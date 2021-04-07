package com.agilethought.atsceapi.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.agilethought.atsceapi.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

	@Override
	List<User> findAll();

	@Query("{ 'email' : ?0, 'password' : ?1 }")
	List<User> findUserWithCredentials(String email, String password);

	boolean existsByEmail(String email);
}