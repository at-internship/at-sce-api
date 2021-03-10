package com.agilethought.atsceapi.repository;

import com.agilethought.atsceapi.model.History;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends MongoRepository<History, String> {
}
