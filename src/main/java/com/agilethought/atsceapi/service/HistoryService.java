package com.agilethought.atsceapi.service;

import com.agilethought.atsceapi.dto.NewHistoryRequest;
import com.agilethought.atsceapi.dto.NewHistoryResponse;

public interface HistoryService {
    NewHistoryResponse createHistory(NewHistoryRequest request);
}
