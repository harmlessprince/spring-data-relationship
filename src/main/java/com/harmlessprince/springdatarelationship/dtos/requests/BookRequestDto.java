package com.harmlessprince.springdatarelationship.dtos.requests;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class BookRequestDto {
    private String name;
    private List<Integer> authorIds;
    private Integer categoryId;
}
