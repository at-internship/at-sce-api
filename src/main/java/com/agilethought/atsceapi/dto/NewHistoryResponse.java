package com.agilethought.atsceapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
public class NewHistoryResponse {
    @Id
    private String id;
}
