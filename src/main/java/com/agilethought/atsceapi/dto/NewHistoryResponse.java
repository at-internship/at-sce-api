package com.agilethought.atsceapi.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class NewHistoryResponse {
    @Id
    private String id;
}
