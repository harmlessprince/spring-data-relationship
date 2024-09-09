package com.harmlessprince.springdatarelationship.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthorRequestDto {

    private String name;
    private Integer zipCodeId;
}
