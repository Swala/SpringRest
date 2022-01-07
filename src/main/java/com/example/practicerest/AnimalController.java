package com.example.practicerest;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/api/animals")
public class AnimalController {

    AnimalService animalService;

    /*
    List<Animal> animals = List.of(new Animal(UUID.randomUUID().toString(), "Lion", "Panthera leo", "", ""),
            new Animal(UUID.randomUUID().toString(), "Goat", "Capra hircus", "", ""),
            new Animal(UUID.randomUUID().toString(), "Elephant", "Loxodonta", "", ""));*/

    @GetMapping
    public List<Animal> all(){

        return animalService.all().map(AnimalController::toDTO)
              .collect(Collectors.toList());
        //return animalService.all().stream().map(AnimalController::toDTO).collect(Collectors.toList());
    }


    @PostMapping
    public Animal createAnimal(@RequestBody CreateAnimal createAnimal){
        return toDTO(animalService.createAnimal(createAnimal.getName(), createAnimal.getBinomialName()));
   }

   @GetMapping("/{id}")
   public ResponseEntity<Animal> get(@PathVariable String id){
        try{
            return ResponseEntity.ok(toDTO(animalService.get(id)));
        }catch (AnimalNotFoundException e){
            return ResponseEntity.notFound().build();
        }

   }

    //PUT HTTP method is used to modify/update a resource where the client sends data that updates the entire resource.
    //PUT overwrites the entire entity if it already exists, and creates a new resource if it doesn’t exist.
   @PutMapping("/{id}")
   public ResponseEntity<Animal> update(@PathVariable("id") String id, @RequestBody UpdateAnimal updateAnimal){
    try {
        return ResponseEntity.ok(toDTO(animalService.updateAnimal(id, updateAnimal.getName(), updateAnimal.getBinomialName())));
    } catch (AnimalNotFoundException e){
        return ResponseEntity.notFound().build();
    }
   }

   @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id){
        try {
            animalService.delete(id);
            return ResponseEntity.ok().build();
        } catch (AnimalNotFoundException e){
            return ResponseEntity.notFound().build();
        }

   }

   @GetMapping("/{id}/link/{remoteId}")
   public ResponseEntity<Animal> link(@PathVariable("id") String id, @PathVariable("remoteId") String remoteId){
        try{
            return ResponseEntity.ok(toDTO(animalService.link(id, remoteId)));
        }catch (AnimalNotFoundException e){
            return ResponseEntity.notFound().build();
        }

   }

    private static Animal toDTO(AnimalEntity animalEntity) {
        return new Animal(
                animalEntity.getId(),
                animalEntity.getName(),
                animalEntity.getBinomialName(),
                animalEntity.getDescription(),
                animalEntity.getConservationStatus()
        );
    }



}
