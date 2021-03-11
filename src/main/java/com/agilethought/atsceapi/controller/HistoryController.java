package com.agilethought.atsceapi.controller;

import com.agilethought.atsceapi.dto.NewHistoryRequest;
import com.agilethought.atsceapi.dto.NewHistoryResponse;
import com.agilethought.atsceapi.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
}
