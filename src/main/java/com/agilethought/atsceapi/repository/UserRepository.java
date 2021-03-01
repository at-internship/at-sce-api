package com.agilethought.atsceapi.repository;

import com.agilethought.atsceapi.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    @Query("{ 'email' : ?0, 'password' : ?1 }")
    List<User> findUsersByEmail(String email, String password);
}