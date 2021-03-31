package com.agilethought.atsceapi.service;

import java.util.List;

import com.agilethought.atsceapi.dto.*;

public interface HistoryService {
    
	NewHistoryResponse createHistory(NewHistoryRequest request);

	List<HistoryDTO> getAllHistory(String id);
}