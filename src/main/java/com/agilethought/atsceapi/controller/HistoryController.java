package com.agilethought.atsceapi.controller;

import java.util.List;

import com.agilethought.atsceapi.dto.history.HistoryDTO;
import com.agilethought.atsceapi.dto.history.NewHistoryRequest;
import com.agilethought.atsceapi.dto.history.NewHistoryResponse;
import com.agilethought.atsceapi.service.AuthorizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.agilethought.atsceapi.service.HistoryService;

import io.swagger.annotations.*;

@RestController
@RequestMapping(value = "/api/v1")
@Api(value = "History Api")
@Slf4j
public class HistoryController {
    
	@Autowired
    private HistoryService historyService;

	@Autowired
	private AuthorizationService authorizationService;

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
    public NewHistoryResponse postHistory(@RequestBody NewHistoryRequest request,
										  @RequestHeader(value = "authorization") String authorization)
			throws Exception {
		authorizationService.authorizeRequest(authorization);
        return historyService.createHistory(request);

    }
    
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/histories")
	@ApiOperation(value="Obtain all histories created by a user (through user id)")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "The resource has been retrieved successfully"),
    		@ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Access prohibited"),
            @ApiResponse(code = 404, message = "Not Found"),
    })
	public List<HistoryDTO> getHistory(@RequestParam("userid") String id,
									   @RequestHeader(value = "authorization") String authorization)
			throws Exception {
    	authorizationService.authorizeRequest(authorization);
		return historyService.getUserHistories(id);

	}

}
