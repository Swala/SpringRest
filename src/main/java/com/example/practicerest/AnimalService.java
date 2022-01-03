package com.example.practicerest;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class AnimalService {
    AnimalRepo animalRepo;
    JsonPlaceHolderRemote jsonPlaceHolderRemote;


    public Stream<AnimalEntity> all(){
        return animalRepo.all();
    }


    public AnimalEntity createAnimal(String name, String binomialName){
        AnimalEntity animalEntity = new AnimalEntity(UUID.randomUUID().toString(), name, binomialName, "", "");
        return animalRepo.save(animalEntity);

    }


    public AnimalEntity get(String id) throws AnimalNotFoundException{
        return animalRepo.get(id)
                .orElseThrow(() -> new AnimalNotFoundException(id));
    }

    public AnimalEntity updateAnimal(String id, String name, String binomialName) throws AnimalNotFoundException{
        AnimalEntity animalEntity = animalRepo.get(id)
                .orElseThrow(() -> new AnimalNotFoundException(id));

        animalEntity.setName(name);
        animalEntity.setBinomialName(binomialName);
        return animalRepo.save(animalEntity);

    }


    public void delete(String id) throws AnimalNotFoundException{
        AnimalEntity animalEntity = animalRepo.get(id)
                .orElseThrow(()-> new AnimalNotFoundException(id));
        System.out.println(id);
        System.out.println(animalEntity.toString());
        animalRepo.delete(animalEntity);

    }

    public AnimalEntity link(String id, String remoteId) throws AnimalNotFoundException{
        AnimalEntity animalEntity = animalRepo.get(id)
                .orElseThrow(()-> new AnimalNotFoundException(id));
        JsonPlaceHolderRemote.JsonPlaceholder json = jsonPlaceHolderRemote.get(remoteId);
        animalEntity.setDescription(json.getBody());
        return animalRepo.save(animalEntity);
    }
}