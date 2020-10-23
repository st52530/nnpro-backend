package cz.upce.vetalmael.service.implementation;

import cz.upce.vetalmael.model.Animal;
import cz.upce.vetalmael.model.dto.AnimalDto;
import cz.upce.vetalmael.repository.AnimalRepository;
import cz.upce.vetalmael.service.AnimalService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "animalService")
public class AnimalServiceImpl implements AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void addAnimal(AnimalDto animalDto) {
        Animal animal = modelMapper.map(animalDto,Animal.class);
        animalRepository.save(animal);
    }

    @Override
    public void editAnimal(AnimalDto animalDto) {
        Animal animal = modelMapper.map(animalDto,Animal.class);
        animalRepository.save(animal);
    }

    @Override
    public void removeAnimal(int idAnimal) {
        animalRepository.deleteById(idAnimal);
    }
}
