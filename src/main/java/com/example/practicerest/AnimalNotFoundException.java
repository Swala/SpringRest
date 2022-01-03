package com.example.practicerest;

public class AnimalNotFoundException extends Exception {
    public AnimalNotFoundException(String id){
        super(id);
    }
}
