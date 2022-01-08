package com.example.practicerest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;



@Repository
public interface AnimalRepo extends JpaRepository <AnimalEntity, String> {

    //Map<String, AnimalEntity> animals = new HashMap<>();

    default Optional <AnimalEntity> get(String id){
        Optional<AnimalEntity> animalEntity = findById(id);
        return animalEntity;
    }

}
