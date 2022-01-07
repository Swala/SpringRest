package com.example.practicerest;

import lombok.Value;

import java.util.UUID;

@Value
public class Animal {
    String id;
    String name;
    String binomialName;
    String description;
    String conservationStatus;


}
