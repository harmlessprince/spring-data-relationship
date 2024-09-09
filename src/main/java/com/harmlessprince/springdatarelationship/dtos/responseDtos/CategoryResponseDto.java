package com.harmlessprince.springdatarelationship.dtos.responseDtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CategoryResponseDto {

    private Integer id;

    private String name;

    private List<String> bookNames;
}
