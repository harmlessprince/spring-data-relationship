package com.harmlessprince.springdatarelationship.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ZipCodeRequestDto {
    private Integer cityId;
    private String name;
}
