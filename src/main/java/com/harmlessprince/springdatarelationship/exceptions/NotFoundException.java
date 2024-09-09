package com.harmlessprince.springdatarelationship.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String itemName, Integer itemId) {
        super(itemName + " with id: " + itemId + " could not be found");
    }

//    public NotFoundException(String message) {
//        super(message);
//    }
}
