package com.agilethought.atsceapi.service;

import java.util.List;

import com.agilethought.atsceapi.dto.NewHistoryRequest;
import com.agilethought.atsceapi.dto.NewHistoryResponse;
import com.agilethought.atsceapi.model.History;

public interface HistoryService {
    NewHistoryResponse createHistory(NewHistoryRequest request);

	List<History> getAllHistory(String id);
}
