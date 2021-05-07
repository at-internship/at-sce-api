package com.agilethought.atsceapi.service;

import static com.agilethought.atsceapi.exception.ErrorMessage.NOT_FOUND_RESOURCE;
import static com.agilethought.atsceapi.exception.ErrorMessage.USER;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.agilethought.atsceapi.dto.history.*;
import com.agilethought.atsceapi.dummy.DummyNewHistoryRequest;
import com.agilethought.atsceapi.exception.NotFoundException;
import com.agilethought.atsceapi.model.History;
import com.agilethought.atsceapi.model.User;
import com.agilethought.atsceapi.population.HistoryPopulation;
import com.agilethought.atsceapi.repository.*;
import com.agilethought.atsceapi.service.implementation.HistoryServiceImpl;
import com.agilethought.atsceapi.validator.history.HistoryValidator;

import ma.glasnost.orika.MapperFacade;

@RunWith(SpringJUnit4ClassRunner.class)
public class HistoryServiceImplTest {
	public DummyNewHistoryRequest dummyNewHistoryRequest;

	@Mock
	public UserRepository userRepository;

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
		dummyNewHistoryRequest = new DummyNewHistoryRequest();
		dummyNewHistoryRequest.populateHistoryRequest();
	}

	@Test
	public void testGetAllHistorySuccessfully() {
		when((historyRepository).findAllByUserId(anyString())).thenReturn(new ArrayList<>());
		when((orikaMapperFacade).mapAsList(anyList(), any())).thenReturn(new ArrayList<>());
		when((userRepository).findById(anyString())).thenReturn(Optional.of(new User()));
		List<HistoryDTO> result = historyService.getUserHistories("15781212");
		assertNotNull(result);
	}

	@Test
	public void testGetAllHistoryWithIdNotFound() {
		when((userRepository).findById(anyString())).thenReturn(Optional.empty());
		when((historyRepository).findAllByUserId(anyString())).thenReturn(new ArrayList<>());
		Exception notFoundMessage = assertThrows(NotFoundException.class, () -> {
			historyService.getUserHistories("15781212");
		});
		assertEquals(String.format(NOT_FOUND_RESOURCE, USER, "15781212"), notFoundMessage.getMessage());
	}

	@Test
	public void testCreateHistory() {

		doNothing().when(historyValidator).validate(any());
		when((orikaMapperFacade).map(any(NewHistoryRequest.class), any())).thenReturn(new History());
		when((historyRepository).save(any())).thenReturn(new History());
		when((orikaMapperFacade).map(any(History.class), any())).thenReturn(new NewHistoryResponse());

		NewHistoryResponse result = historyService.createHistory(dummyNewHistoryRequest);

		assertNotNull(result);
	}
}