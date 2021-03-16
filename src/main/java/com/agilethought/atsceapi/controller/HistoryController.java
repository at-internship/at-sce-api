package com.agilethought.atsceapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.agilethought.atsceapi.dto.NewHistoryRequest;
import com.agilethought.atsceapi.dto.NewHistoryResponse;
import com.agilethought.atsceapi.model.History;
import com.agilethought.atsceapi.service.HistoryService;

@RestController
@RequestMapping(value = "/api/v1")
public class HistoryController {
    @Autowired
    private HistoryService historyService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/histories", consumes = "application/json", produces = "application/json")
    public NewHistoryResponse postHistory(@RequestBody NewHistoryRequest request) {
        return historyService.createHistory(request);
    }
    
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/histories")
	public List<History> getHistory(@RequestParam("userid") String id){
		return historyService.getAllHistory(id);
	}
}
