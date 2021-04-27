package com.agilethought.atsceapi.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.agilethought.atsceapi.dto.history.NewHistoryRequest;
import com.agilethought.atsceapi.dto.history.NewHistoryResponse;
import com.agilethought.atsceapi.service.HistoryService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(HistoryController.class)
public class HistoryControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private HistoryService historyService;

	private static final String REQUEST_MAPPING = "/api/v1";

	@Test
	public void testGetHistory() throws Exception {

		String getMapping = "/histories?userid=1234";
		when(historyService.getUserHistories(anyString())).thenReturn(new ArrayList<>());
		mockMvc.perform(get(REQUEST_MAPPING + getMapping).contentType(APPLICATION_JSON)).andExpect(status().isOk());

	}

	@Test
	public void testPostHistory() throws Exception {

		String postMapping = "/histories";
		when(historyService.createHistory(any(NewHistoryRequest.class))).thenReturn(new NewHistoryResponse());
		mockMvc.perform(post(REQUEST_MAPPING + postMapping).content("{}").contentType(APPLICATION_JSON))
				.andExpect(status().isCreated());

	}

}
