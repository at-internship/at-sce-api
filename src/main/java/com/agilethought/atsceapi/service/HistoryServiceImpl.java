package com.agilethought.atsceapi.service;

import com.agilethought.atsceapi.dto.NewHistoryRequest;
import com.agilethought.atsceapi.dto.NewHistoryResponse;
import com.agilethought.atsceapi.model.History;
import com.agilethought.atsceapi.repository.HistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HistoryServiceImpl implements HistoryService{
    @Autowired
    private HistoryRepository historyRepository;

    @Override
    public NewHistoryResponse createHistory(NewHistoryRequest request) {
        // Manual mapping from NewHistoryRequest to History classes.
        History newHistory = new History(
                null,
                request.getType().byteValue(),
                request.getUser_id(),
                request.getFixedExpenses(),
                request.getTotalHours(),
                request.getTotalDays(),
                request.getCostDay(),
                request.getCostHour(),
                request.getProjectCost(),
                request.getTaxIVA(),
                request.getTaxISR_r(),
                request.getTaxIVA_r(),
                request.getTotal(),
                request.getRevenue(),
                request.getStatus() > 0
        );
        History savedHistory = historyRepository.save(newHistory);
        return new NewHistoryResponse(savedHistory.getId());
    }
}
