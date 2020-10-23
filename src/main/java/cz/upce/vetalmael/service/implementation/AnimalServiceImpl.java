package cz.upce.vetalmael.service.implementation;

import cz.upce.vetalmael.model.dto.AnimalDto;
import cz.upce.vetalmael.repository.AnimalRepository;
import cz.upce.vetalmael.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "animalService")
public class AnimalServiceImpl implements AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    @Override
    public void addAnimal(AnimalDto animalDto) {

    }

    @Override
    public void editAnimal(AnimalDto animalDto) {

    }

    @Override
    public void removeAnimal(int idAnimal) {

    }
}
