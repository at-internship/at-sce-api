package com.agilethought.atsceapi.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.agilethought.atsceapi.model.History;

@Repository
public interface HistoryRepository extends MongoRepository<History, String> {
	
	@Query("{ 'user_id' : ?0 }")
	List<History> findAllById(String id);
}
