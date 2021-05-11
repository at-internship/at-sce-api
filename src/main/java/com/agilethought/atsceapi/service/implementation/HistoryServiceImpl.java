package com.agilethought.atsceapi.service.implementation;

import static com.agilethought.atsceapi.exception.ErrorMessage.NOT_FOUND_RESOURCE;
import static com.agilethought.atsceapi.exception.ErrorMessage.USER;

import java.util.List;
import java.util.Optional;

import com.agilethought.atsceapi.service.HistoryService;
import com.agilethought.atsceapi.population.HistoryPopulation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agilethought.atsceapi.dto.history.*;
import com.agilethought.atsceapi.exception.NotFoundException;
import com.agilethought.atsceapi.model.*;
import com.agilethought.atsceapi.repository.*;
import com.agilethought.atsceapi.validator.history.HistoryValidator;

import ma.glasnost.orika.MapperFacade;

@Service
public class HistoryServiceImpl implements HistoryService {

	@Autowired
	private HistoryRepository historyRepository;

	@Autowired
	private HistoryValidator historyValidator;

	@Autowired
	private MapperFacade orikaMapperFacade;

	@Autowired
	private UserRepository userRepository;

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

		Optional<User> userFound = userRepository.findById(userId);
		List<History> historyList = historyRepository.findAllByUserId(userId);

		if (userFound.isPresent())
			return orikaMapperFacade.mapAsList(historyList, HistoryDTO.class);

		throw new NotFoundException(String.format(NOT_FOUND_RESOURCE, USER, userId));
	}
}