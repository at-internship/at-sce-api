package com.agilethought.atsceapi.service;

import java.util.List;

import com.agilethought.atsceapi.population.HistoryPopulation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.agilethought.atsceapi.dto.*;
import com.agilethought.atsceapi.model.History;
import com.agilethought.atsceapi.repository.HistoryRepository;
import com.agilethought.atsceapi.validator.HistoryValidator;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;

@Service
@Slf4j
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private HistoryValidator historyValidator;

    @Autowired
    private MapperFacade orikaMapperFacade;

    @Override
    public NewHistoryResponse createHistory(NewHistoryRequest request) {
        historyValidator.validate(request);
        History newHistory = orikaMapperFacade.map(request, History.class);
        HistoryPopulation.populate(newHistory);
        History savedHistory = historyRepository.save(newHistory);
        return orikaMapperFacade.map(savedHistory, NewHistoryResponse.class);
    }

    @Override
    public List<HistoryDTO> getAllHistory(String id) {
        List<History> historyList = historyRepository.findAllById(id);
        return orikaMapperFacade.mapAsList(historyList, HistoryDTO.class);
    }
}