package cz.upce.vetalmael.service.implementation;

import cz.upce.vetalmael.model.Animal;
import cz.upce.vetalmael.model.User;
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
    public Animal addAnimal(AnimalDto animalDto, int idUser) {
        Animal animal = new Animal();
        animal.setName(animalDto.getName());
        User user = new User();
        user.setIdUser(idUser);
        animal.setOwner(user);
        return animalRepository.save(animal);
    }

    @Override
    public Animal editAnimal(AnimalDto animalDto, int idAnimal, int idUser) {
        Animal animal = new Animal();
        animal.setIdAnimal(idAnimal);
        animal.setName(animalDto.getName());
        User user = new User();
        user.setIdUser(idUser);
        animal.setOwner(user);
        return animalRepository.save(animal);
    }

    @Override
    public void removeAnimal(int idAnimal) {
        animalRepository.deleteById(idAnimal);
    }
}
