package com.harmlessprince.springdatarelationship.dtos.responseDtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class BookResponseDto {

    private Integer id;

    private String name;

    private List<String> authorNames;

    private  String category;


}
