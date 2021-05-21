package com.agilethought.atsceapi.adaptor.sso;

import org.springframework.http.ResponseEntity;

public interface SSOAdaptor {
	ResponseEntity<String> getTokenCheck(String token) throws Exception;
}