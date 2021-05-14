package com.agilethought.atsceapi.healthcheck;

import org.apache.logging.log4j.Logger;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.net.Socket;

@Component
public class SSOApiHealthIndicator implements HealthIndicator {

    private static final String URL
            = "https://at-sso-api-qa.herokuapp.com/api/v1";

    @Override
    public Health health() {

        try (Socket socket =
                     new Socket(new java.net.URL(URL).getHost(),80)) {
        } catch (Exception e) {
            Logger log = null;
            log.warn("Failed to connect to: {}",URL);
            return Health.down()
                    .withDetail("error", e.getMessage())
                    .build();
        }
        return Health.up().build();
    }
}
