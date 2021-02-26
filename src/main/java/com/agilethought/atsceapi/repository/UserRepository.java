package com.agilethought.atsceapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.agilethought.atsceapi.model.Users;

@Repository
public interface UserRepository extends MongoRepository<Users, String> {

}
