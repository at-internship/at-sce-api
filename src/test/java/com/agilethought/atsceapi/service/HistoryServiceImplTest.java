package com.agilethought.atsceapi.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.agilethought.atsceapi.domain.FixedExpenses;
import com.agilethought.atsceapi.dto.history.HistoryDTO;
import com.agilethought.atsceapi.dto.history.NewHistoryRequest;
import com.agilethought.atsceapi.dto.history.NewHistoryResponse;
import com.agilethought.atsceapi.model.History;
import com.agilethought.atsceapi.population.HistoryPopulation;
import com.agilethought.atsceapi.repository.HistoryRepository;
import com.agilethought.atsceapi.service.implementation.HistoryServiceImpl;
import com.agilethought.atsceapi.validator.history.HistoryValidator;

import ma.glasnost.orika.MapperFacade;

@RunWith(SpringJUnit4ClassRunner.class)
public class HistoryServiceImplTest {

	@Mock
	public MapperFacade orikaMapperFacade;

	@Mock
	public HistoryRepository historyRepository;

	@Mock
	public static HistoryPopulation historyPopulation;

	@Mock
	public HistoryValidator historyValidator;

	@InjectMocks
	public HistoryServiceImpl historyService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetAllHistory() {
		when((historyRepository).findAllByUserId(anyString())).thenReturn(new ArrayList<>());
		when((orikaMapperFacade).mapAsList(anyList(), any())).thenReturn(new ArrayList<>());

		List<HistoryDTO> result = historyService.getUserHistories("15781212");

		assertNotNull(result);
	}

	@Test
	public void testCreateHistory() {
		FixedExpenses fixedExpenses = new FixedExpenses();
		fixedExpenses.setRent(1.0);
		fixedExpenses.setTransport(1.0);
		fixedExpenses.setInternet(1.0);
		fixedExpenses.setFeed(1.0);
		fixedExpenses.setOthers(1.0);
		fixedExpenses.setTotal(5.0);

		NewHistoryRequest newHistoryRequest = new NewHistoryRequest();
		newHistoryRequest.setType(1);
		newHistoryRequest.setUser_id("1");
		newHistoryRequest.setFixedExpenses(fixedExpenses);
		newHistoryRequest.setTotalHours(1);
		newHistoryRequest.setTotalDays(1);
		newHistoryRequest.setCostDay(1.0);
		newHistoryRequest.setCostHour(1.0);
		newHistoryRequest.setProjectCost(1.0);
		newHistoryRequest.setTaxIVA(1.0);
		newHistoryRequest.setTaxISR_r(1.0);
		newHistoryRequest.setTaxIVA_r(1.0);
		newHistoryRequest.setTotal(1.0);
		newHistoryRequest.setRevenue(1.0);
		newHistoryRequest.setStatus(1);

		doNothing().when(historyValidator).validate(any());
		when((orikaMapperFacade).map(any(NewHistoryRequest.class), any())).thenReturn(new History());
		when((historyRepository).save(any())).thenReturn(new History());
		when((orikaMapperFacade).map(any(History.class), any())).thenReturn(new NewHistoryResponse());

		NewHistoryResponse result = historyService.createHistory(newHistoryRequest);

		assertNotNull(result);
	}
}
