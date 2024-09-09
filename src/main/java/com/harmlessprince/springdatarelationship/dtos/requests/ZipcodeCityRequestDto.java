package com.harmlessprince.springdatarelationship.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ZipcodeCityRequestDto {
    private Integer zipCodeId;
    private Integer cityId;
}
