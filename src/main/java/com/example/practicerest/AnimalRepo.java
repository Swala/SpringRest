package com.example.practicerest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Stream;


@Repository
public interface AnimalRepo extends JpaRepository <AnimalEntity, String> {

    Map<String, AnimalEntity> animals = new HashMap<>();

/*
    default Stream<AnimalEntity> all(){

        //animals.put(UUID.randomUUID().toString(), new AnimalEntity("Lion", "Panthera leo", "", ""));
        return animals.values().stream();
    }
    */
/*
    default AnimalEntity save(AnimalEntity animalEntity){
        animals.put(animalEntity.getId(), animalEntity);

        return animalEntity;
    }*/

    default Optional <AnimalEntity> get(String id){
        return Optional.ofNullable(animals.get(id));
    }

    default void delete(AnimalEntity animalEntity) {
        animals.remove(animalEntity.getId());

    }

}
