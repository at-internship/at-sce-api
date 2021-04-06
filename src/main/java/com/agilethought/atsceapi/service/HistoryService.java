package com.agilethought.atsceapi.service;

import java.util.List;

import com.agilethought.atsceapi.dto.history.HistoryDTO;
import com.agilethought.atsceapi.dto.history.NewHistoryRequest;
import com.agilethought.atsceapi.dto.history.NewHistoryResponse;

public interface HistoryService {
    
	NewHistoryResponse createHistory(NewHistoryRequest request);

	List<HistoryDTO> getUserHistories(String userId);

}