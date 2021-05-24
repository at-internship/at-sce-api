package com.agilethought.atsceapi.adaptor.sso;

import com.agilethought.atsceapi.exception.ForbiddenException;
import com.agilethought.atsceapi.model.User;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.agilethought.atsceapi.exception.UnauthorizedException;

import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

import static java.util.Objects.nonNull;

@Slf4j
@Component
public class SSOAdaptorImpl implements SSOAdaptor {

    private static final String TOKEN_PATH_VARIABLE = "/tokens/";
    private static final String TOKEN_QUERY_VARIABLE = "/tokens?id=";
    private static final String USER_BY_ID = "/users/";

    @Value("${token.path.query.enable}")
    private Boolean tokenQueryEnable;

    @Value("${sso.url}")
    private String rootUrl;

    @Value("${sce.client.id}")
    private String clientId;

    @Value("${sce.client.secret}")
    private String clientSecret;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public ResponseEntity<String> getTokenCheck(String token) throws Exception {
        ResponseEntity<String> response = null;
        try {
            String tokenPath = tokenQueryEnable ? TOKEN_QUERY_VARIABLE : TOKEN_PATH_VARIABLE;
            String url = rootUrl + tokenPath + token;
            HttpHeaders headers = createHeaders();
            HttpEntity<String> httpEntity = new HttpEntity<>(headers);
            log.info("Calling SSO: {}", url);
            response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
        } catch (HttpClientErrorException e) {
            log.error("Error calling SSO token check endpoint: {} - {}", e.getRawStatusCode(), e.getStatusText());
            switch (e.getRawStatusCode()) {
                case 401:
                    throw new UnauthorizedException(e.getMessage());
                case 403:
                    throw new ForbiddenException(e.getMessage());
                default:
                    throw e;
            }
        } catch (Exception e) {
            log.error("Unexpected error occurred while calling SSO token check endpoint: {}", e.getMessage());
            throw e;
        }

        return response;
    }

    @Override
    public boolean existsById(String userId) {
        try {
            ResponseEntity<User> response = null;
            String url = rootUrl + USER_BY_ID + userId;
            HttpHeaders headers = createHeaders();
            HttpEntity<User> httpEntity = new HttpEntity<>(headers);
            log.info("Validating UserId: {} with SSO-api : {}", userId, url);
            response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, User.class);
            log.info("Response from {} : {} ", url, response.getBody());
            return nonNull(response.getBody()) && response.getBody().getId().equals(userId);
        } catch (Exception ex) {
            log.error("Error occurred while validating userId {} in SSO-api : {}", userId, ex.getMessage());
            return false;
        }
    }

    private HttpHeaders createHeaders() {
        return new HttpHeaders() {{
            String auth = clientId + ":" + clientSecret;
            byte[] encodedAuth = Base64.encodeBase64(
                    auth.getBytes(StandardCharsets.US_ASCII));
            String authHeader = "Basic " + new String(encodedAuth);
            set("Authorization", authHeader);
        }};
    }
}