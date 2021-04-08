package com.agilethought.atsceapi.service.implementation;

import java.util.List;

import com.agilethought.atsceapi.service.HistoryService;
import com.agilethought.atsceapi.population.HistoryPopulation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agilethought.atsceapi.dto.history.HistoryDTO;
import com.agilethought.atsceapi.dto.history.NewHistoryRequest;
import com.agilethought.atsceapi.dto.history.NewHistoryResponse;
import com.agilethought.atsceapi.model.History;
import com.agilethought.atsceapi.repository.HistoryRepository;
import com.agilethought.atsceapi.validator.history.HistoryValidator;

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
	public List<HistoryDTO> getUserHistories(String userId) {

		List<History> historyList = historyRepository.findAllByUserId(userId);
		return orikaMapperFacade.mapAsList(historyList, HistoryDTO.class);

    }

}