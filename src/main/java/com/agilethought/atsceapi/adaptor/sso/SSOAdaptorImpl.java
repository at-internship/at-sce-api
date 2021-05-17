package com.agilethought.atsceapi.adaptor.sso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.agilethought.atsceapi.exception.UnauthorizedException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SSOAdaptorImpl implements SSOAdaptor {
	private static final String TOKEN_PATH = "/tokens/";

	@Value("${sso.url}")
	String rootUrl;

	@Autowired
	RestTemplate restTemplate;

	@Override
	public ResponseEntity<String> getTokenCheck(String token) throws Exception {
		ResponseEntity<String> response = null;
		try {
			String url = rootUrl + TOKEN_PATH + token;
			response = restTemplate.getForEntity(url, String.class);
		} catch (HttpClientErrorException e) {
			log.error("Error calling SSO token check endpoint: {} - {}", e.getRawStatusCode(), e.getStatusText());
			switch (e.getRawStatusCode()) {
			case 401:
				throw new UnauthorizedException(e.getMessage());
			case 403:
				throw new UnauthorizedException(e.getMessage());
			default:
				throw e;
			}
		} catch (Exception e) {
			log.error("Unexpected error occurred while calling SSO token check endpoint: {}", e.getMessage());
			throw e;
		}

		return response;
	}

}