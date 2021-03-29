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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/api/v1")
@Api(value = "Api de History")
public class HistoryController {
    @Autowired
    private HistoryService historyService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/histories", consumes = "application/json", produces = "application/json")
    @ApiOperation(value="Create a new history")
    @ApiResponses(value = {
    		@ApiResponse(code = 201, message = "Resource created successfully"),
    		@ApiResponse(code = 400, message = "Bad request"),
    		@ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Access prohibited"),
            @ApiResponse(code = 404, message = "Not Found"),
    })
    public NewHistoryResponse postHistory(@RequestBody NewHistoryRequest request) {
        return historyService.createHistory(request);
    }
    
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/histories")
	@ApiOperation(value="Obtain a specific history by user id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "The resource has been retrieved successfully"),
    		@ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Access prohibited"),
            @ApiResponse(code = 404, message = "Not Found"),
    })
	public List<History> getHistory(@RequestParam("userid") String id){
		return historyService.getAllHistory(id);
	}
}
